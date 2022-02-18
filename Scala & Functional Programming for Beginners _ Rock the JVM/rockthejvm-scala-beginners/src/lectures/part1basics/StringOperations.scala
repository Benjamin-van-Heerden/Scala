package lectures.part1basics

object StringOperations extends App{
	val str: String = "Hello, I am learning Scala"

	println(str.charAt(2))
	println(str.substring(7, 11))
	println(str.split(" ").toList)
	println(str.startsWith("Hello"))
	println(str.replace(" ", "_"))
	println(str.toLowerCase())
	println(str.length)

	val aNumberStr = "42"
	val aNumber = aNumberStr.toInt

	println("a" +: aNumberStr :+ "z")

	println(str.reverse)

	println(str.take(2))

	// Scala-specific: String Interpolators

	// S-interpolators
	val name = "Ben"
	val age = 12
	val greeting = s"Hi $name, you are $age"
	val anotherGreeting = s"Hi $name, you will be ${age + 1}"
	println(greeting)
	println(anotherGreeting)

	// F-Interpolators
	val speed = 1.2f
	val myth = f"$name can eat $speed%2.2f burgers per second" // %2.2f -> %(2 chars minimum).(2 decimals precision)

	println(myth)

	// raw-Interpolators
	println(raw"this is a \n newline")
	println("this is a \n newline")
}
