package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  println(x)

  //  x = 2

  // VALS ARE IMMUTABLE

  // COMPILER can infer types

  val aString: String = "Hello"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'

  val aInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 123456789L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables *can be reassigned
  var aVariable: Int = 4
  aVariable = 5 // side effects

  // we will prefer the use of val, consistent with the functional paradigms of programming



}
