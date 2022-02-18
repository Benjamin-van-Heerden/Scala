package lectures.part1basics

import sun.font.TrueTypeFont

object Expressions extends App {
	val x = 1 + 2 //EXPRESSION
	println(x)
	println(2 + 2 + 4)
	// + - * / & | ^ << >> >>> (bitwise expressions)

	println(1 == x)
	// == != > >= < <= (comparison)

	println(!(1 == x))
	// ! && || (logical expressions)

	var aVariable = 2
	aVariable += 3 // also works with -= *= /= ... side effects

	// Instructions (DO) vs Expressions (VALUE)

	// IF expression
	val aCondition = true
	val aConditionedValue = if(aCondition) 5 else 3 //IF EXPRESSION -> gives a value
	print(aConditionedValue)

	// There are loops in Scala but their use is discouraged
	var i = 0
	while (i < 10) {
		println(i)
		i += 1
	}
	// NEVER WRITE THIS AGAIN.

	// Looping is an imperative paradigm, Scala is declarative

	// Everything in Scala is an Expression

	val aWeirdValue = (aVariable = 3) // type here is Unit
	println(aWeirdValue)

	// side effects: println(), whiles, reassigning

	// Code blocks

	val aCodeBlock = {
		val y = 2
		val z = y + 1

		if (z > 2) "hello" else "goodbye"
	} // code blocks evaluate to the last expression in them, the type of the above code block is therefore a string

	// val anotherValue = z + 1 // this will not work since z is scoped within the code block and cannot be used outside

	// 1. difference between "hello world" vs println("hello world")
	// 2.

	val someValue = {
		2 < 3
	}

	val someOtherValue = {
		if (someValue) 239 else 986
		42
	}

}
