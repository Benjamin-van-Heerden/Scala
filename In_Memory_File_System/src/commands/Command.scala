package commands

import filesystem.State


trait Command {
	def apply(state: State): State
}

object Command {
	val MKDIR = "mkdir"
	val LS = "ls"
	val PWD = "pwd"
	val TOUCH = "touch"
	val CD = "cd"
	val RM = "rm"
	val CAT = "cat"
	val ECHO = "echo"

	def emptyCommand: Command = new Command {
		override def apply(state: State): State = state
	}

	def unknownOrIncompleteCommand(name: String): Command = new Command {
		override def apply(state: State): State = {
			state.setMessage(s"'$name': unknown or incomplete command")
		}
	}


	def from(input: String): Command = {
		val tokens: List[String] = input.split(" ").toList

		if (input.nonEmpty) {
			tokens.head match {
				case MKDIR => {
					if (tokens.length != 2) unknownOrIncompleteCommand(MKDIR)
					else new Mkdir(tokens(1))
				}
				case LS => {
					if (tokens.length != 1) unknownOrIncompleteCommand(LS)
					else new Ls
				}
				case PWD => {
					if (tokens.length != 1) unknownOrIncompleteCommand(PWD)
					else new Pwd
				}
				case TOUCH => {
					if (tokens.length != 2) unknownOrIncompleteCommand(TOUCH)
					else new Touch(tokens(1))
				}
				case CD => {
					if (tokens.length != 2) unknownOrIncompleteCommand(CD)
					else new Cd(tokens(1))
				}
				case RM => {
					if (tokens.length != 2) unknownOrIncompleteCommand(RM)
					else new Rm(tokens(1))
				}
				case ECHO => {
					if (tokens.length < 2) unknownOrIncompleteCommand(ECHO)
					else new Echo(tokens.tail.toList)
				}
				case CAT => {
					if (tokens.length != 2) unknownOrIncompleteCommand(CAT)
					else new Cat(tokens(1))
				}

				case _ => new UnknownCommand(input)
			}
		} else {
			emptyCommand
		}
	}
}
