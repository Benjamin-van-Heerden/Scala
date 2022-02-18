package lectures.part2oop

object InheritanceAndTraits extends App {

	// Scala offers only single class inheritance
	class Animal {
		val creatureType = "wild"
		//private def eats: Unit = println("nomnomnom") //use only within the "Animal" instance itself
		def eat: Unit = println("nomnomnom") // use anywhere
		//protected def eat: Unit = println("nomnomnom") // use within "Animal" and any of its subclasses
	}

	// Cat is a subclass of Animal and Animal is a Superclass of Cat
	class Cat extends Animal {
		def crunch: Unit = {
			eat
			println("crunch crunch")
		}
	}

	val animal = new Animal

	val cat = new Cat
	cat.crunch

	//constructors

	class Person(name: String, age: Int) {
		def this(name: String) = this(name, 0)
	}
	class Adult(name: String, age: Int, idCard: String) extends Person(name) //need a valid constructor

	//overriding

	class Dog extends Animal {
		override val creatureType: String = "domestic" //can also override directly in constructor
		override def eat = println("crunch, crunch")
	}

	val dog: Dog = new Dog
	dog.eat
	println(dog.creatureType)

	class Fish(override val creatureType: String) extends Animal {
		def move: String = "swim"
		override def eat = println("gulp, gulp")
		def superEat = super.eat
	}

	// type substitution (broad: polymorphism)
	val unknownAnimal: Animal = new Fish("Swimming")


	unknownAnimal.eat
	// methods will always go to the "most overridden one" whenever possible

	println(unknownAnimal.getClass) // is a fish which is itself an animal
	// but be careful, since we declare fish to be an animal it only has access to methods defined for the super class
	// "Animal" of which there is only one, which is overridden

	val fish = new Fish("swimming")


	// super

	//println(unknownAnimal.move) //wont work
	//unknownAnimal.superEat // also wont work
	//these are fine

	fish.eat
	fish.superEat

	//preventing overrides

	//1 - use "final" modifier on method i.e. final def eat ...
	//2 - use "final" modifier on class itself i.e. final class Animal ... this prevents the entire class from being extended as well
	//3 - seal the class = extend classes IN THIS FILE ONLY ( sealed class Animal ...)


}
