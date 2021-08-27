package commands
import files.{DirEntry, Directory}
import filesystem.State

class Mkdir(name: String) extends Create(name) {
	override def createSpecificEntry(state: State): Directory = {
		Directory.empty(state.wd.path, name)
	}
}
