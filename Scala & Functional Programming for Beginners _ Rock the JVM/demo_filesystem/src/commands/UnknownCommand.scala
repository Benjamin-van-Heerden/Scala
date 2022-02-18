package commands
import filesystem.State

class UnknownCommand(input: String) extends Command {
	override def apply(state: State): State = {
		state.setMessage(s"Command '$input' not found")
	}
}
