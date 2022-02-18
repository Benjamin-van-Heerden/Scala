package lectures.part2oop

import scala.util.control.Breaks.break

object Exceptions extends App {
	val x: String = null
	//println(x.length) // will crash with NullPointerException

	// how do we deal with exceptions?

	//throw new NullPointerException
	// this ^^ will also crash the program. "we throw an exception and there is no one there to catch it"

	//1. Throwing exceptions
	//val aWeirdValue: String = throw new NullPointerException

	//throwable classes extend the Throwable class.
	// Exception and Error are the major Throwable subtypes

	//2. how do we catch them?
	def getInt(withExceptions: Boolean): Int = {
		if (withExceptions) throw new RuntimeException("No int for you")
		else 42
	}

	val potentialFail = try {
		//code that might fail
		getInt(false)
	} catch {
		case e: RuntimeException => 43
	} finally {
		//code that will get executed no matter what
		// finally block is optional
		// does not influence the return type of this expression
		// use finally only for side effects e.g. logging to a file
		println("finally")
	}

	println(potentialFail)

	//3. define own exceptions

	class MyException extends Exception
	val exception = new MyException
	//throw exception
	//^^ will crash program

	/*
	1. crash your program with an OutOfMemoryError
	2. crash with SOError
	3. PocketCalculator
		- add(x, y)
		- multiply(x, y)
		- subtract(x, y)
		- divide(x, y)

		Throw
			- OverflowException if add(x, y) exceeds Int.MAX_VALUE
			- UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
			- MathCalculationException for division by zero
	*/

	//1. OOM
	//val array = Array.ofDim(Int.MaxValue)

	//2. SO
	//def infinite: Int = 1 + infinite
	//val noLimit = infinite

	class OverFlowException extends Exception
	class UnderFlowException extends Exception
	class MathCalculationException extends Exception

	object PocketCalculator {
		def add(x: BigInt, y: BigInt): BigInt = {
			val result = x + y
			if (result > Int.MaxValue) throw new OverFlowException
			else if (result < Int.MinValue) throw new UnderFlowException
			x + y
		}
		def subtract(x: BigInt, y: BigInt): BigInt = {
			val result: BigInt = x - y
			if (result > Int.MaxValue) throw new OverFlowException
			else if (result < Int.MinValue) throw new UnderFlowException
			x - y
		}
		def multiply(x: BigInt, y: BigInt): BigInt = {
			val result: BigInt = x*y
			if (result > Int.MaxValue) throw new OverFlowException
			else if (result < Int.MinValue) throw new UnderFlowException
			x*y
		}
		def divide(x: BigInt, y: BigInt): Float = {
			if (y == 0) throw new MathCalculationException
			(x/y).toFloat
		}
	}

	println(PocketCalculator.add(1, 2))
	println(PocketCalculator.add(Int.MaxValue, -10))
	println(PocketCalculator.subtract(Int.MinValue, -10))
	println(PocketCalculator.multiply(Int.MaxValue, 0))
	println(PocketCalculator.divide(3, 1))

}
