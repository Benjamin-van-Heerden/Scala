package lectures.part3fp

object WhatsAFunction extends App {
	// DREAM: use functions as first class elements
	// problem: oop

	val doubler = new MyFunction[Int, Int] {
		override def apply(element: Int): Int = element * 2
	}

	println(doubler(2))

	// function types = Function1[A, B]

	val stringToIntConverter = new Function[String, Int] {
		override def apply(string: String): Int = string.toInt
	}

	println(stringToIntConverter("3") + 5)

	val adder = new Function2[Int, Int, Int] {
		override def apply(v1: Int, v2: Int): Int = v1 + v2
	}

	// Function types Function2[A, B, R] === ((A, B) => R)

	// ALL SCALA FUNCTIONS ARE OBJECTS

	/*
	1. function which takes two strings and concatenates them
	2. transform MyPredicate and MyTransformer into function types
	3. define a function which takes an int and returns another functions which takes an int and returns an int
		- what is the type of this function
		- how to do it
	 */

	//1
	val concat: (String, String) => String = new Function2[String, String, String] {
		override def apply(v1: String, v2: String): String = v1 + v2
	}

	println(concat("Hello ", "Scala"))

	//3
	// Function1[Int, Function1[Int, Int]]

	val specialFunction: (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y

	val adder3 = specialFunction(3)

	println(adder3(4)) //curried function
}

trait MyFunction[A, B] {
	def apply(element: A): B
}
