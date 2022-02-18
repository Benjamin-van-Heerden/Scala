package lectures.part2oop

object Objects {
	// SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
	object Person { // type + its only instance, there can be no other instance of the "Person" type
		val N_EYES: Int = 2
		def canFly: Boolean = false

		// factory method => build "Persons" from parameters
		def from(mother: Person, father: Person): Person = new Person("Bob")
		// can call them with conveniently with apply, "looks like constructor
		def apply(mother: Person, father: Person): Person = new Person("Kyle")
	}
	class Person(val name: String = "Default") {
		// instance level functionality
	}
	// COMPANIONS
	def main(args: Array[String]): Unit = { // this is what the extends App qualifier does
		println(Person.N_EYES)
		println(Person.canFly)

		// Scala object = SINGLETON INSTANCE
		val mary = Person
		val john = Person
		println(mary == john) //true

		val maryInstance = new Person // => companion class "Person"
		val johnInstance = new Person
		println(maryInstance == johnInstance) //false

		val bob = Person.from(maryInstance, johnInstance)
		//equivalently
		val kyle = Person(maryInstance, johnInstance) // apply makes callable like a function

		// Scala Applications = Scala object with and important method
		// def main(args: Array[String]): Unit
	}


}
