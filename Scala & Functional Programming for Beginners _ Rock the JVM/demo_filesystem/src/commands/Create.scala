package commands

import files.{DirEntry, Directory}
import filesystem.State

abstract class Create(name: String) extends Command {
	override def apply(state: State): State = {
		val wd = state.wd
		if (wd.hasEntry(name)) {
			state.setMessage(s"Entry: '$name' already exists")
		}
		else if (name.contains(Directory.SEPARATOR)) {
			// mkdir something/somethingElse -> Not allowed for simplicity
			state.setMessage(s"'$name' must not contain separators")
		}
		else if (checkIllegal(name)) {
			state.setMessage(s"Illegal entry name: '$name'")
		}
		else {
			doCreate(name, state)
		}
	}

	def checkIllegal(name: String): Boolean = {
		name.contains(".")
	}

	def doCreate(name: String, state: State): State = {
		val wd = state.wd

		def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
			if (path.isEmpty) currentDirectory.addEntry(newEntry)
			else {
				val oldEntry = currentDirectory.findEntry(path.head).asDirectory
				currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
			}
		}

		// 1. all the directories in the full path
		val allDirsInPath = wd.getAllFoldersInPath

		// 2.  create new directory entry in the wd
//		val newDir = Directory.empty(wd.path, name)
		val newEntry: DirEntry = createSpecificEntry(state)

		// 3. update the whole directory structure starting from the root
		val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

		// 4. fine new wd INSTANCE given wds full path in the NEW directory structure
		val newWd = newRoot.findDescendant(allDirsInPath)

		State(newRoot, newWd)
	}

	def createSpecificEntry(state: State): DirEntry
}
