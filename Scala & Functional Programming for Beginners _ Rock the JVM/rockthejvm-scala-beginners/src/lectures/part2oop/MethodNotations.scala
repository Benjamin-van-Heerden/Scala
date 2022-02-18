package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
	class Person(val name: String, favoriteMovie: String, val age: Int) {
		def likes(movie: String): Boolean = movie == favoriteMovie
		def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
		def unary_! : String = s"$name, what the heck?" //note the space before the colon
		def isAlive: Boolean = true
		def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

		//exercises
		def +(nickName: String): Person = new Person(s"$name ($nickName)", favoriteMovie, age)
		def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

		def learns(skill: String): String = s"$name learns $skill"
		def learnsScala: String = this learns "Scala" //very cool

		def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
	}

	val mary = new Person("Mary", "Inception", 22)
	println(mary.likes("Inception"))
	//infix notation, only works on methods with one parameter (syntactic sugar)
	println(mary likes "Inception")

	// "operators" in Scala
	val tom = new Person("Tom", "Fight Club", 25)
	println(mary + tom)
	println(mary.+(tom))

	// math methods are acting the exact same way as methods, the symbols for these methods are not reserved

	println(1 + 2)
	println(1.+(2))

	// ALL OPERATORS ARE METHODS.
	// Akka actors have ! ?

	//prefix notation

	val x = -1 // equivalent with 1.unary_-
	val y = 1.unary_-
	// unary_prefix only works with + - ~ !

	println(!mary)
	//same as
	println(mary.unary_!)

	//postfix notation (only available to methods without params)
	// not as useful as prefix or others as the only difference in implementation is a "."
	println(mary.isAlive)
	println(mary isAlive)

	// apply, very special in Scala and we will make heavy use of it
	println(mary.apply())
	//same as
	println(mary())

	/*
	1.	Overload + operator
			mary + "the rockstar" => new person "Mary (the rockstar)"

	2.	Add an age to the Person class
			Add a unary + operator => new Person with the age + 1
			+mary => mary with the age incremented

	3.	Add a "learns" method in the Person class => "Mary learns Scala"
	 	additionally, add a learnsScala method,	calls learns method with "Scala". Use it in postfix notation

	4.	Overload the apply method
		mary.apply(2) => "Mary watched Inception 2 times"

	*/

	//
	val maryTheRockstar = mary + "the rockstar"
	println(maryTheRockstar.name)
	println((+maryTheRockstar).age) //careful for order of execution +maryTheRockstar.age => +22 = 22

	println(maryTheRockstar learns "knitting")
	println(maryTheRockstar learnsScala)

	println(maryTheRockstar.apply(2))

}
