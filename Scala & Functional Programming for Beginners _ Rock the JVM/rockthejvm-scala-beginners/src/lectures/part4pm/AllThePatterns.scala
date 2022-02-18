package lectures.part4pm

import exercises.{Cons, EmptyList, MyList}

import scala.collection.View.Empty

object AllThePatterns extends App {
	// 1- constants

	val x: Any = "Scala"
	val constants = x match {
		case 1 => "a number"
		case "Scala" => "THE Scala"
		case true => "The Truth"
		case AllThePatterns => "A singleton object"
		case _ => "nothing"
	}

	// 2 - match anything
	// 2.1 wildcard

	val matchAnything = x match {
		case _ => "anything"
	}

	val matchAVariable = x match {
		case something => s"I have found $something"
		case _ => "nothing"
	}

	// 3 - tuples

	val aTuple = (1, 2)

	val matchATuple = aTuple match {
		case (1, 1) => "a"
		case (something, 2) => s"b $something"
		case _ => "nothing"
	}

	val nestedTuple = (1, (2, 3))

	val matchNestedTuple = nestedTuple match {
		case (_, (2, v)) => s"found $v"
		case _ => "nothing"
	}

	// PMs can be NESTED

	// 4 - case classes -> constructor pattern
	// PM can be nested with case classes as well
	val myList: MyList[Int] = Cons(1, Cons(2, EmptyList))

	val matchAList = myList match {
		case EmptyList => "This is empty"
		case Cons(head, Cons(subhead, subtail)) => "Found structure"
		case _ => "nothing"
	}

	// 5 - list patterns
	val aStandardList = List(1, 2, 3, 42)
	val standardListMatching = aStandardList match {
		case List(1, _, _, _) => "list" //extractor - advanced
		case List(1, _*) => "list of arbitrary length"
		case 1 :: List(_) => "infix pattern"
		case List(1, 2, 3) :+ 42 => "another infix pattern"
		case _ => "nothing"
	}

	// 6 - type specifications

	val unknown: Any = 2
	val unknownMatch = unknown match {
		case list: List[Int] => "this is a list" //explicit type specifier
		case _ => "anything"
	}

	// 7 - name binding

	val nameBindingMatch = myList match {
		case nonEmptyList @ Cons(_, _) => "name binding"
		case Cons(1, rest @ Cons(2, _)) => "can use 'rest' later"
		case _ => "nothing"
	}

	// 8 - multi-patterns

	val multiPattern = myList match {
		case EmptyList | Cons(0, _) => "compound pattern (multi-pattern"
		case _ => "nothing"
	}

	// 9 - if guards
	val secondElementSpecial = myList match {
		case Cons(_, Cons(special, _)) if special % 2 == 0 => "something special"
		case _ => "nothing"
	}

	/*
	Question.

	 */

	val numbers = List(1, 2, 3)
	val numbersMatch = numbers match {
		case listOfStrings: List[String] => "a list of strings"
		case listOfInts: List[Int] => "a list of ints"
		case _ => "somehting else"
	}

	println(numbersMatch)
	// JVM trick question
	// JVM cannot see generic types (type erasure)

}
