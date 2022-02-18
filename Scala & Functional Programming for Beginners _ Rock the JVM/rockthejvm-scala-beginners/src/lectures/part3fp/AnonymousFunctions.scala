package lectures.part3fp

object AnonymousFunctions extends App {
	val double = new Function[Int, Int] {
		override def apply(v1: Int): Int = v1 * 2
	}
	//equivalently

	//anonymous function or (LAMBDA)
	val anotherDouble: Int => Int = x => x * 2

	//multiple parameters
	val adder: (Int, Int) => Int = (x, y) => x + y
	val adder2 = (a: Int, b: Int) => a + b

	println(adder(1, 2))

	// no parameters

	val justDoSomething = () => 3

	println(justDoSomething) // LAMBDAS MUST BE CALLED WITH PARENTHESIS
	println(justDoSomething())

	// curly braces with lambdas
	val stringToInt = (a: String) => {
		a.toInt
	}

	println(stringToInt("54"))

	// more syntactic sugar
	val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1

	val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (x, y) => x + y

	/*
	1. Go to MyList and replace FunctionX calls with lambdas
	2. rewrite curried adder as anonymous function
	 */

	//2

	val curriedAdder = (x: Int) => (y: Int) => x + y
	println(curriedAdder(3)(4))
}
