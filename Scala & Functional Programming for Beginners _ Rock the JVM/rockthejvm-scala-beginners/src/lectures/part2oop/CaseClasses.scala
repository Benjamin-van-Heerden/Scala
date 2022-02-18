package lectures.part2oop

object CaseClasses extends App {
	case class Person(name: String, age: Int)
	// 1. class parameters are promoted to fields

	val jim = new Person("Jim", 34)
	println(jim.age) //wouldn't be possible otherwise

	//2. sensible toString
	println(jim.toString) //powerful for debugging
	//even better (println(instance) = println(instance.toString)
	println(jim)

	//3. equals and hashCode implemented OOTB
	val jim2 = new Person("Jim", 34)

	println(jim == jim2) //without case class this would be false since they are separate instances

	//4. CCs have handy copy methods
	val jim3 = jim.copy(age = 45)
	println(jim == jim3)

	//5. CCs have companion objects automatically
	val thePerson = Person
	//also some nice factory methods
	val mary = Person("Mary", 23) //apply method
	//don't use new when instantiating a case class

	//6. CCs are serializable
	//Akka

	//7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING!!

	//there are also case objects

	case object UnitedKingdom {
		def name: String = "The UK of GB and NI"
	}

	/*
	Expand MyList - use case classes and case objects
	*/
}
