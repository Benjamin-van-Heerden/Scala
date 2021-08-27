package commands
import files.{DirEntry, File}
import filesystem.State

class Touch(name: String) extends Create(name) {
	override def createSpecificEntry(state: State): File = {
		File.empty(state.wd.path, name)
	}
}
