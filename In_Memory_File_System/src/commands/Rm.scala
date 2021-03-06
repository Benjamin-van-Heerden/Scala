package commands
import files.Directory
import filesystem.State

class Rm(name: String) extends Command {
	override def apply(state: State): State = {
		// 1. get wd
		val wd = state.wd

		// 2. get abs path
		val absolutePath = {
			if (name.startsWith(Directory.SEPARATOR)) name
			else if (wd.isRoot) wd.path + name
			else wd.path + Directory.SEPARATOR + name
		}

		// 3. do some checks
		if (Directory.ROOT_PATH.equals(absolutePath)) {
			state.setMessage("I'm sorry, Dave. I'm afraid I can't do that.")
		}
		else {
			doRm(state, absolutePath)
		}
	}

	def doRm(state: State, path: String): State = {
		// 4. find entry to rm
		// 5. update structure
		def rmHelper(currentDirectory: Directory, path: List[String]): Directory = {
			if (path.isEmpty) currentDirectory
			else if (path.tail.isEmpty) currentDirectory.removeEntry(path.head)
			else {
				val nextDirectory = currentDirectory.findEntry(path.head)
				if (!nextDirectory.isDirectory) currentDirectory
				else {
					val newNextDirectory = rmHelper(nextDirectory.asDirectory, path.tail)
					if (newNextDirectory == nextDirectory) currentDirectory
					else currentDirectory.replaceEntry(path.head, newNextDirectory)
				}
			}
		}

		val tokens = path.substring(1).split(Directory.SEPARATOR).toList
		val newRoot: Directory = rmHelper(state.root, tokens)

		if (newRoot == state.root) {
			state.setMessage(s"$path: no such file or directory")
		}
		else {
			State(newRoot, newRoot.findDescendant(state.wd.path.substring(1)))
		}


	}
}
