package lectures.part1basics

object CBNvsCBV extends App{
	def calledByValue(x: Long): Unit = {
		println(s"by value $x")
		println(s"by value $x") // this works as one would expect
	}

	def calledByName(x: => Long): Unit = {
		println(s"by name $x") // println(s"by name $System.nanoTime()")
		println(s"by name $x") // println(s"by name $System.nanoTime()") // which is why we get different results
	}

	calledByValue(System.nanoTime())
	calledByName(System.nanoTime())

	def infinite(): Int = 1 + infinite()
	def printFirst(x: Int, y: => Int) = println(x)

	// printFirst(infinite(), 34) // Would crash (overflow
	printFirst(34, infinite()) // => delays evaluation of expression until it is needed, since we never use y in printFirst our program survives.
}
