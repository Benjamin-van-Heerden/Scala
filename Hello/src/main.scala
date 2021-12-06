object main extends App {
  val s = "hello world"
  println(s)

  val stringFun = (input: String) => String {
    s"Hello $input"
  }

  println(stringFun("World"))
}
