@main def test = {
  case class BadInput(msg: String) extends Exception(msg)

  def validatedInput(): Unit =
    println("Input")
    try 
      val inp = getInput()
      println(s"Your input was validated as: $inp")
    catch
        case BadInput(msg) => println(msg); validatedInput()
        case ex: Exception => println("fatal error, aborting"); throw ex

  def getInput(): String =
    val text = scala.io.StdIn.readLine()
    if text.startsWith("A") then throw BadInput("May not start with 'A'")
    else if text.startsWith("B") then throw Exception("No!")
    else text

  validatedInput()

}

