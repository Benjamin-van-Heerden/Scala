package lectures.part3fp

object HOFsAndCurries extends App {
	// how do we read a function like this?
	// val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???
	//^^ higher order function (HOF) - function as a parameter

	//e.g. map, flatMap, filter

	// function that applies a function n times over a value x
	// nTimes(f, n, x)
	// nTimes(f, 3, x) = f(f(f(x)))

	def nTimes(f: (Int) => Int, n: Int, x: Int): Int = {
		if (n <= 0) x
		else nTimes(f, n-1, f(x))
	}

	val plusOne = (x: Int) => x + 1

	println(nTimes(plusOne, 10, 1))

	// curried version of the nTimes function
	def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
		if (n <= 0) (x: Int) => x
		else (x: Int) => nTimesBetter(f, n-1)(f(x))
	}

	val plusTen = nTimesBetter(plusOne, 10)

	println(plusTen(1))

	// curried functions
	// arrow sign is RIGHT ASSOCIATIVE
	val superAdder = (x: Int) => (y: Int) => x + y
	val add3 = superAdder(3)

	println(add3(10))

	// functions with multiple parameter lists
	def curriedFormatter(c: String)(x: Double): String = c.format(x)

	val standardFormat: (Double => String) = curriedFormatter("%4.2f")
	val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

	println(standardFormat(Math.PI))
	println(preciseFormat(Math.PI))

	/*
	1. Expand MyList
		- forEach method A => Unit
		[1, 2, 3].foreach(x => println(x)) ->
		x
		x
		x

		- sort function ((A, A) => Int) => MyList
		[1, 2, 3].sort((x, y) => y - x) -> [3, 2, 1]

		- zipWith (myList, (A, A) => B) => MyList
		[1, 2, 3].zip([4, 5, 6], x*y) -> [4, 10, 18]

		- fold(start)(function) => a value
		[1, 2, 3].fold(0)(x+y) = 6

	2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)

	3. fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

	4. compose(f, g) => x => f(g(x))

	5. andThen(f, g) => x => g(f(x))
	 */

	//2
	def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
		(x: Int) => (y: Int) => f(x, y)
	}

	def sum(x: Int, y: Int): Int = x + y

	def c = toCurry(sum)

	println(c(42)(2))

	//3
	def fromCurry(f: Int => Int => Int): (Int, Int) => Int = {
		(x: Int, y: Int) => f(x)(y)
	}

	def curriedSum(x: Int)(y: Int): Int = x + y

	def d = fromCurry(curriedSum)

	println(d(2, 69))

	//4**
	def compose(f: Int => Int, g: Int => Int): Int => Int = (x: Int) => f(g(x))

	println(compose((x: Int) => x*2, (y: Int) => y + 2)(3))

	//5**
	def andThen(f: Int => Int, g: Int => Int): Int => Int = (x: Int) => g(f(x))

	println(andThen((x: Int) => x*2, (y: Int) => y + 2)(3))

	def typedCompose[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))

	def typedAndThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

	def superAdder2: (Int => Int => Int) = toCurry(_ + _)
	def add4 = superAdder2(4)
	println(add4(17))

	val simpleAdder = fromCurry(superAdder2)
	println(simpleAdder(4, 17))

	val add2 = (x: Int) => x + 2
	val times3 = (x: Int) => x*3

	val composed = compose(add2, times3)
	val ordered = andThen(add2, times3)

	println(composed(3))
	println(ordered(3))
}
