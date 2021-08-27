package files

import filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, val contents: String) extends DirEntry(parentPath, name) {
	override def path: String = super.path

	override def asDirectory: Directory = throw new FileSystemException("A File cannot be converted to a directory")

	override def getType: String = "File"

	override def asFile: File = this

	override def isDirectory: Boolean = false

	override def isFile: Boolean = true

	def setContents(newContent: String): File = {
		new File(parentPath, name, newContent)
	}

	def appendContents(newContent: String): File = {
		setContents(contents + "\n" + newContent)
	}
}

object File {
	def empty(parentPath: String, name: String): File = {
		new File(parentPath, name, "")
	}
}
