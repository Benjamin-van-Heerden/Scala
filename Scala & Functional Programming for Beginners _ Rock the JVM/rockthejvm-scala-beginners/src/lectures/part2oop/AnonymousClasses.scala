package lectures.part2oop

object AnonymousClasses extends App {
	abstract class Animal {
		def eat: Unit
	}

	// instantiated an anonymous class
	val funnyAnimal: Animal = new Animal {
		override def eat: Unit = println("hahah")
	}
	/*
	what the compiler does behind the scenes
	class AnonymousClasses$$anon$1 extends Animal {
		override def eat: Unit = println("hahah")
	}
	val funnyAnimal = new AnonymousClasses$$anon$1
	 */

	println(funnyAnimal.getClass)

	class Person(name: String) {
		def sayHi: Unit = println(s"Hi, my name is $name")
	}

	val jim = new Person("Jim") {
		override def sayHi: Unit = println("Hi, my name is Jim")
	}

	// anonymous classes work for both abstract and non abstract classes
	// NB need to implement all abstract methods or fields

	/*
	Expand MyList:
	1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
	2. Generic trait MyTransformer[-A, B] with method transform(A) => B
	3. MyList:
		- map(transformer) => MyList
		- filter(predicate) => MyList
		- flatMap(transformer from A to MyList[B]) => MyList[B]

		class EvenPredicate extends MyPredicate[Int]
		class StringToIntTransformer extends MyTransformer[String, Int]

	[1, 2, 3].map(n*2) = [2, 4, 6]
	[1, 2, 3, 4].filter(n % 2) = [2, 4]
	[1, 2, 3].flatMap(n => [n, n+1]) => [1, 2, 2, 3, 3, 4]
	 */

}
