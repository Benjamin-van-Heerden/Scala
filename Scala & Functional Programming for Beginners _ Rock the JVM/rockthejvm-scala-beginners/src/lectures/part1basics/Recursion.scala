package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App{
	def factorial(n: Int): Int = {
		//@tailrec // compiler won't be able to resolve this decorator
		if (n <= 1) 1
		else {
			println(s"Computing factorial of $n - I first need factorial of ${n-1}")
			val result = n*factorial(n-1)
			println(s"Computed factorial of $n")
			result
		}
	}
	// println(factorial(10))
	// println(factorial(5000)) // this will fail due to overflow

	def anotherFactorial(n: Int): BigInt = {
		@tailrec
		def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
			if (x <= 1) accumulator
			else factorialHelper(x-1, x*accumulator) // TAIL RECURSION = use recursive call as the LAST expression
		}
		factorialHelper(n, 1)
	}

	println(anotherFactorial(5000))

	// Why does anotherFactorial work while the plain factorial does not?
	// In simple, the main difference between the traditional recursion and tail recursion is when the actual
	// calculation takes place. In traditional recursion, calculation will happen after the recursion call while the
	// calculation will occur before the recursion call in tail recursion.


	// WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION.

	//1. Concatenate a string n times
	@tailrec //don't need this decorator, but it will help the compiler recognise errors
	def concatStr(s: String, n: Int, accumulator: String = ""): String = {
		if (n <= 1) accumulator
		else concatStr(s, n-1, accumulator+s)
	}

	println(concatStr("Hi", 10))

	//2. IsPrime function tail recursive
	def isPrime(n: BigInt): Boolean = {
		@tailrec
		def isPrimeUpTo(t: BigInt, accumulator: Boolean = true): Boolean = {
			if (!accumulator) false
			else if (t <= 1) accumulator
			else isPrimeUpTo(t-1, accumulator && n % t != 0)
		}
		isPrimeUpTo(n-1)
	}

	println(isPrime(10))
	println(isPrime(1234567))
	println(isPrime(31))
	println(isPrime((scala.math.pow(2, 19) - 1).toInt)) // Mersenne Prime

	//3. Fibonacci Tail recursive
	@tailrec
	def fibonacci(n: BigInt, accumulator1: BigInt = 1, accumulator2: BigInt = 1): BigInt = {
		if (n <= 2) accumulator1
		else fibonacci(n-1, accumulator1 + accumulator2, accumulator1)
	}

	println(fibonacci(10))
	println(fibonacci(123456))





}
