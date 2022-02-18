package lectures.part3fp

object MapFlatMapFilterFor extends App {
	val list = List(1, 2, 3)
	println(list)

	println(list.head)
	println(list.tail)

	//map
	println(list.map(_ + 3))
	println(list.map(_ + " is a number"))

	//filter
	println(list.filter(_ != 2))
	println(list.filter(_ % 2 == 0))

	//flatMap
	val toPair = (x: Int) => List(x, x + 1)
	println(list.flatMap(toPair))

	// print all combinations between two lists
	val numbers = List(1, 2, 3, 4)
	val chars = List("a", "b", "c", "d")
	val colors = List("black", "white")

	def Combinations(x: String, list: List[Int]): List[String] = {
		if (list.isEmpty) List()
		else List(x + list.head.toString) ++ Combinations(x, list.tail)
	}

	def allCombinations(l1: List[String], l2: List[Int]): List[String] = {
		if (l1.isEmpty) List()
		else Combinations(l1.head, l2) ++ allCombinations(l1.tail, l2)
	}

	println(allCombinations(chars, numbers))

	//alternatively
	val combs = numbers.flatMap(n => chars.map(c => "" + c + n))
	println(combs)
	// "iterations"
	val numCharsCols = numbers.flatMap(n => chars.flatMap(c => colors.map(col => "" + c + n + "-" + col)))
	println(numCharsCols)

	list.foreach(println)

	// for-comprehensions

	val forCombinations = for {
		n <- numbers if n % 2 == 0 //this is an if-guard
		c <- chars
		color <- colors
	} yield "" + c + n + "-" + color

	println(forCombinations)

	for {
		n <- numbers
	} println(n)

	// syntax overload
	list.map { x =>
		x*2
	}

	/*
	1. MyList supports for comprehensions?
	2. A small collection of at most ONE element - Maybe[+T]
		- map, flatMap, filter
	*/

}
