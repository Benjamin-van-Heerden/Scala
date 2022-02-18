package lectures.part2oop

object Generics extends App {
	class MyList[+A] {
		//use type A
		def add[B >: A](element: B): MyList[B] = ???
		/*
		A = Cat
		B = Dog = Animal (what B amounts to)
		 */
	}

	class MyMap[key, value]
	val listOfIntegers = new MyList[Int]
	val listOfStrings = new MyList[String]

	// generic methods
	object MyList {
		def empty[A]: MyList[A] = ???
	}

	val emptyListOfIntegers = MyList.empty[Int]

	// variance problem
	class Animal
	class Cat extends Animal
	class Dog extends Animal

	// does List[Cat] extend List[Animal]?
	// 1. yes, this is called COVARIANCE

	class CovariantList[+A]

	val animal: Animal = new Cat

	val animalList: CovariantList[Animal] = new CovariantList[Cat]
	// animalList.add(new Dog) ?? => the returned list will be a list of animals

	// 2. NO = INVARIANT
	class InvariantList[A]
	val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

	// 3. Hell, no! => CONTRAVARIANCE
	class ContravariantList[-A]

	val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

	class Trainer[-A]
	val trainer: Trainer[Cat] = new Trainer[Animal]

	//bounded types
	class Cage[A <: Animal](animal: A) // <: == only accept subtypes of, similarly >: only supertypes of
	val cage = new Cage(new Dog)

	class Car
	// val newCage = new Cage(new Car)


	//expand MyList to be Genereic

}
