package commands
import files.{Directory, File}
import filesystem.State


class Echo(args: List[String]) extends Command {
	override def apply(state: State): State = {
		/*
		if no args, state
		else if just one arg, print to console
		else if multiple args {
			operator = next to last argument
			if >
				echo to a file (may create if not there)
			if  >>
				append to file
			else
				just echo everything to console
		}
		 */



		if (args.isEmpty) state
		else if (args.length == 1) state.setMessage(args(0))
		else {
			val operator = args(args.length - 2)
			val filename = args(args.length - 1)
			val contents = args.take(args.length - 2).mkString(" ")

			operator match {
				case ">" => doEcho(state, contents, filename, append=false)
				case ">>" => doEcho(state, contents, filename, append=true)
				case _ => state.setMessage(args.mkString(" "))
			}
		}
	}

	def getRootAfterEcho(currentDirectory: Directory, path: List[String], contents: String, append: Boolean): Directory = {
		/*
		if path is empty, fail (currentDirectory)
		else if no more things to explore = path.tail.isEmpty
			find the file create/add content to
			if file not found create file
			else if the entry is actually a directory, then fail
			else
				replace or append content to the file
				replace the entry with the filename with the NEW file
		else
			find next directory to navigate
			call gRAE recursively on thay
			else replace entry with the directory after the recursive call
		 */

		if (path.isEmpty) currentDirectory
		else if (path.tail.isEmpty) {
			val dirEntry = currentDirectory.findEntry(path.head)

			if (dirEntry == null) currentDirectory.addEntry(new File(currentDirectory.path, path.head, contents))
			else if (dirEntry.isDirectory) currentDirectory
			else {
				if (append) currentDirectory.replaceEntry(path.head, dirEntry.asFile.appendContents(contents))
				else currentDirectory.replaceEntry(path.head, dirEntry.asFile.setContents(contents))
			}
		}
		else {
			val nextDirectory = currentDirectory.findEntry(path.head).asDirectory
			val newNextDirectory = getRootAfterEcho(nextDirectory, path.tail, contents, append)

			if (newNextDirectory == nextDirectory) currentDirectory
			else currentDirectory.replaceEntry(path.head, newNextDirectory)
		}
	}

	def doEcho(state: State, contents: String, filename: String, append: Boolean): State = {
		if (filename.contains(Directory.SEPARATOR)) {
			state.setMessage("Echo: filename may not contain separators")
		}
		else {
			val newRoot: Directory = getRootAfterEcho(state.root, state.wd.getAllFoldersInPath :+ filename, contents, append)
			if (newRoot == state.root) state.setMessage(s"$filename: no such file")
			else {
				State(newRoot, newRoot.findDescendant(state.wd.getAllFoldersInPath))
			}
		}
	}
}
