package lectures.part2oop

import java.time.Year

object OOPBasics extends App {
//	val person = new Person("John", 26)
//	println(person.x)
//	println(person.greet("Dan"))
	val author = new Writer("Charles", "Dickens", 1812)
	val imposter = new Writer("Charles", "Dickens", 1812)
	val novel = new Novel("Great Expectations", 1861, author)

	println(novel.authorAge())
	println(novel.isWrittenBy(author))
	println(novel.isWrittenBy(imposter))

	val counter = new Counter()
	counter.inc.printCount
	counter.inc.inc.inc.printCount
	counter.inc(10).printCount
}

//Constructor
class Person(name: String, val age: Int = 0) {
	//body
	val x = 2

	println(1 + 3)

	def greet(name: String): Unit = println(s"${this.name} says: Hi $name")

	//multiple constructors
	def this(name: String) = this(name, 0)

}

// The compiler can infer "this" in many cases
// Class parameters from the constructor are NOT FIELDS

/*1. Novel and Writer class
	Writer: first name, surname, year
		-method fullname
	Novel: name, year_of_release, author
		-authorAge
		-isWrittenBy(author)
		-copy (new year of release) = new instance of Novel
*/

case class Writer(firstName: String, surname: String, val year: Int = 0) {
	def fullName(): String = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
	def authorAge(): Int = yearOfRelease - author.year
	def isWrittenBy(author: Writer): Boolean = author == this.author
	def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

/*2. Counter class
	- receives an int value
	- method current count
	- method to increment/decrement => new Counter
	- overload inc/dec to receive an amount

*/

class Counter(val count: Int = 0) {
	def inc = {
		println("Incrementing")
		new Counter(count + 1)
	} // Immutability
	def dec = {
		println("Decrementing")
		new Counter(count - 1)
	}

	// overload
//	def inc(n: Int) = new Counter(count + n)
//	def dec(n: Int) = new Counter(count - n)

	//suppose we want to print incrementing/decrementing for logging purposes for each of n
	// i.e. we want only the one method for each of "inc" or "dec"

	def inc(n: Int): Counter = {
		if (n <= 0) this
		else inc.inc(n-1) // this effectively calls inc.inc.inc.inc... at the end of the stack frame
	}

	def dec(n: Int): Counter = {
		if (n <= 0) this
		else dec.dec(n-1)
	}

	def printCount = println(count)


}