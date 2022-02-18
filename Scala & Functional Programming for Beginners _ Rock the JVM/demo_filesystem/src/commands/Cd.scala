package commands
import files.{DirEntry, Directory}
import filesystem.State

import scala.annotation.tailrec

class Cd(dir: String) extends Command {
	override def apply(state: State): State = {
		/*
		cd /something/somethingElse/.../
		 */

		// 1. find root
		val root = state.root
		val wd = state.wd

		// 2. find absolute path of dir I want to cd to
		val absolutePath = {
			if (dir.startsWith(Directory.SEPARATOR)) dir
			else if (wd.isRoot) wd.path + dir
			else  wd.path + Directory.SEPARATOR + dir
		}

		// 3. find the directory to cd to
		val destinationDirectory = {
			doFindEntry(root, absolutePath)
		}

		// 4. change the state given the new dir
		if (destinationDirectory == null || !destinationDirectory.isDirectory) {
			state.setMessage(s"$dir: no such directory")
		}
		else {
			State(root, destinationDirectory.asDirectory)
		}
	}

	def doFindEntry(root: Directory, path: String): DirEntry = {
		def collapseRelativeTokens(path: List[String], result: List[String] = List()): List[String] = {
			if (path.isEmpty) result
			else if (".".equals(path.head)) collapseRelativeTokens(path.tail, result)
			else if ("..".equals(path.head)) {
				if (result.isEmpty) null
				else collapseRelativeTokens(path.tail, result.init)
			}
			else collapseRelativeTokens(path.tail, result :+ path.head)
		}

		def findEntryHelper(currentDirectory: Directory, path: List[String]): DirEntry = {
			if (path.isEmpty || path.head.isEmpty) currentDirectory
			else if (path.tail.isEmpty) currentDirectory.findEntry(path.head)
			else {
				val nextDir = currentDirectory.findEntry(path.head)
				if (nextDir == null || !nextDir.isDirectory) null
				else findEntryHelper(nextDir.asDirectory, path.tail)
			}
		}

		val tokens = path.substring(1).split(Directory.SEPARATOR).toList
		// ["a", "."] => ["b"]

		// /a/../ => ["a", ".."] => []

		// /a/b/.. => ["a", "b", ".."] => ["a"]

		val newTokens = collapseRelativeTokens(tokens)

		if (newTokens == null) null
		else findEntryHelper(root, newTokens)
	}
}
