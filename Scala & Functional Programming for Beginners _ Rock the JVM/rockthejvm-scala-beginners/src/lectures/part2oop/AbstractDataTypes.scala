package lectures.part2oop

object AbstractDataTypes extends App {
	// abstract
	abstract class Animal {
		val creatureType: String
		def eat: Unit
	}

	class Dog extends Animal {
		val creatureType: String = "Canine"
		def eat: Unit = println("Crunch")
	}

	// traits
	trait Carnivore {
		def eat(animal: Animal): Unit
	}

	trait ColdBlooded

	class Crocodile extends Animal with Carnivore with ColdBlooded {
		val creatureType = "Croc"
		def eat: Unit = println("nom nom")
		def eat(animal: Animal): Unit = println(s"I am a croc and I am eating a ${animal.creatureType}")
	}

	val dog = new Dog
	val croc = new Crocodile

	croc eat dog

	//how are traits different from abstract classes? Both can have abstract and non abstract members?
	//1 - traits do not have constructor parameters
	//2 - multiple traits may be inherited by the same class
	//3 - traits == behaviour, abstract class == "thing"
}
