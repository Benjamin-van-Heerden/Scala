package lectures.part4pm

object PatternsEverywhere extends App {
	val err = {
		try {
			throw new RuntimeException
		}
		catch {
			case e: RuntimeException => "runtime"
			case npe: NullPointerException => "npe"
			case _ => "something else"
		}
	}

	println(err)

	// catches are actually MATCHES!!

	val list = List(1, 2, 3, 4)
	val evenOnes = for {
		x <- list if x % 2 == 0
	} yield 10 * x

	// generators are also based on pattern matching
	println(evenOnes)

	val tuples = List((1, 2), (3, 4))
	val filterTuples = for  {
		(first, second) <- tuples
	} yield first * second

	println(filterTuples)

	val tuple = (1, 2, 3)
	val (a, b, c) = tuple //tuple deconstruction

	println(a)

	//not limited to just tuples

	val head :: tail = list
	println(head)
	println(tail)


	// partial functions (based on PM)

	val mappedList = list.map {
		case v if v % 2 == 0 => v + " is even"
		case 1 => "the ONE"
		case _ => "something else"
	} // partial function literal

	println(mappedList)

	// equivalent to

	val mappedList2 = list.map(x => x match {
		case v if v % 2 == 0 => v + " is even"
		case 1 => "the ONE"
		case _ => "something else"
	})

	println(mappedList2)
}
