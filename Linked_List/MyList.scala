package exercises

import lectures.part2oop.Generics.MyList

import scala.runtime.Nothing$

abstract class MyList[+A] {
	/*
	head = first element of the list
	tail = remainder of the list
	isEmpty = is the list empty
	add(int) => new list with this element added
	toString => a string representation of the list
	 */
	def head: A
	def tail: MyList[A]
	def isEmpty: Boolean
	def add[B >: A](element: B): MyList[B]
	def printElements: String
	// polymorphic call
	override def toString: String = "[" + printElements + "]"
	//need override here since toString is a method ALREADY PRESENT in the scala.Any reference

	// higher order functions
	def map[B](transFormer: A => B): MyList[B]
	def filter(predicate: A => Boolean): MyList[A]
	def flatMap[B](transFormer: A => MyList[B]): MyList[B]
	def ++[B >: A](list: MyList[B]): MyList[B]

	//hofs
	def forEach(method: A => Unit): Unit
	def sort(criterion: (A, A) => Int): MyList[A]
	def zipWith[B, C](list: MyList[B], rule: (A, B) => C): MyList[C]
	def fold[B](start: B)(f: (B, A) => B): B
}

case object EmptyList extends MyList[Nothing] {
	override def head: Nothing = throw new NoSuchElementException
	override def tail: MyList[Nothing] = throw new NoSuchElementException
	override def isEmpty: Boolean = true
	override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, EmptyList)
	override def printElements: String = ""

	override def map[B](transformer: Nothing => B): MyList[B] = EmptyList
	override def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList
	override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList

	override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

	//hofs
	override def forEach(method: Nothing => Unit): Unit = ()
	override def sort(criterion: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList
	override def zipWith[B, C](list: MyList[B], rule: (Nothing, B) => C): MyList[C] = {
		if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
		else EmptyList
	}
	override def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
	override def head: A = h
	override def tail: MyList[A] = t
	override def isEmpty: Boolean = false
	override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
	override def printElements: String = {
		if (t.isEmpty) "" + h
		else h + " " + t.printElements
	}

	override def filter(predicate: A => Boolean): MyList[A] = {
		if (predicate(h)) new Cons(h, t.filter(predicate))
		else t.filter(predicate)
	}

	override def map[B](transformer: A => B): MyList[B] = {
		new Cons(transformer(h), t.map(transformer))
	}

	override def ++[B >: A](list: MyList[B]): MyList[B] = {
		new Cons(h, t ++ list)
	}
	/*
	[1, 2] ++ [3, 4]
	= new Cons(1, [2] ++ [3, 4]
	= new Cons(1, new Cons(2, Empty ++ [3, 4])
	= new Cons(1, new Cons(2, new Cons(3, new Cons(4, EmptyList))))
	*/

	override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
		transformer(h) ++ t.flatMap(transformer)
	}
	/*
	[1, 2].flatMap(n => [n, n+1])
	= [1, 2] ++ [2].flatMap(n => [n, n+1])
	= [1, 2] ++ [2, 3] ++ EmptyList.flatMap(n => [n, n+1])
	= [1, 2] ++ [2, 3] ++ EmptyList
	= [1, 2, 2, 3]
	*/

	override def forEach(method: A => Unit): Unit = {
		method(h)
		t.forEach(method)
	}

	override def sort(criterion: (A, A) => Int): MyList[A] = {
		def insert(x: A, sortedList: MyList[A]): MyList[A] = {
			if (sortedList.isEmpty) new Cons(x, EmptyList)
			else if (criterion(x, sortedList.head) <= 0) new Cons(x, sortedList)
			else new Cons(sortedList.head, insert(x, sortedList.tail))
		}
		val sortedTail = t.sort(criterion)
		insert(h, sortedTail)
	}

	override def zipWith[B, C](list: MyList[B], rule: (A, B) => C): MyList[C] = {
		if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
		else new Cons(rule(h, list.head), t.zipWith(list.tail, rule))
	}

	override def fold[B](start: B)(f: (B, A) => B): B = {
		t.fold(f(start, h))(f)
	}
	/*
	[1, 2, 3].fold(0)(+) =
	[2, 3].fold(1)(+) =
	[3].fold(3)(+) =
	[].fold(6)(+) = 6
	 */
}

//trait MyPredicate[-A] { // T => Boolean
//	def test(element: A): Boolean
//}
//
//trait MyTransformer[-A, B] { // A => B
//	def transform(element: A): B
//}



object ListTest extends App {
	val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
	val listOfStrings: MyList[String] = new Cons("a", new Cons("b", new Cons("c", EmptyList)))

	println(listOfInt.toString)
	println(listOfStrings.toString)

	println(listOfInt.map(_ * 2).toString)

	println(listOfInt.add(4).filter(_ != 2).toString)

	val anotherListOfInt: MyList[Int] = new Cons(4, new Cons(5, EmptyList))

	println(listOfInt ++ anotherListOfInt)

	println(listOfInt.flatMap(element => new Cons(element, new Cons(element + 1, EmptyList))).toString)

	val cloneListOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))

	println(cloneListOfInt == listOfInt) // from "case" qualifier

	listOfInt.forEach(println)

	println(listOfInt.sort((x: Int, y: Int) => y - x))

	println(listOfStrings.zipWith(listOfInt, (x: String, y: Int) => s"$x-$y"))

	println(listOfInt.fold(0)(_ + _))

	//for comprehension
	val forComprehension = for {
		n <- listOfInt
		s <- listOfStrings
	} yield n+"-"+s

	println(forComprehension)
}
