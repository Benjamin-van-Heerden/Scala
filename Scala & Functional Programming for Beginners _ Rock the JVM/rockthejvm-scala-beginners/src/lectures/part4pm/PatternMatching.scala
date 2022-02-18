package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
	//switch on steroids

	val random = new Random
	val x = random.nextInt(10)

	val description = x match {
		case 1 => "the ONE"
		case 2 => "the TWO"
		case 3 => "the THREE"
		case _ => "Something else" // _ = WILDCARD
	}

	println(x)
	println(description)

	//1. Decompose values

	case class Person(name: String, age: Int)
	val bob = Person("Bob", 22)

	val greeting = bob match {
		case Person(n, a) if a > 30 => s"Hi I am $n and I am $a years old" //this is a guard
		case Person(n, a) => s"Hi I am $n and I am $a years young"
		case _ => "I don't know who I am"
	}

	println(greeting)

	/*
	1. cases are matched in order
	2. what if no cases match? MatchError
	3. the type of the pm expression? the unified type of all the types in all the cases
	4. PM works really well with case classes
	 */

	// PM on sealed hierarchies

	sealed class Animal
	case class Dog(breed: String) extends Animal
	case class Parrot(greeting: String) extends Animal

	val animal: Animal = Dog("Terra Nova")

	animal match {
		case Dog(someBreed) => println(s"Matched $someBreed")
	}

	// match everything

	val isEven = x match {
		case n if n % 2 == 0 => true
		case _ => false
	}
	// WHY?!

	val properIsEven = x % 2 == 0


	/*
	Exercises
	simple function that uses PM takes Expr => human readable form

	Sum(Number(2), Number(3)) => 2 + 3
	Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
	Prod(Sum(Number(2), Number(3)), Number(4)) => (2 + 3) * 4
	Sum(Prod(Number(2), Number(3)), Number(4)) => 2 * 3 + 4
	 */

	trait Expr
	case class myNumber(n: Int) extends Expr
	case class mySum(e1: Expr, e2: Expr) extends Expr
	case class myProd(e1: Expr, e2: Expr) extends Expr

	def show(e: Expr): String = {
		e match {
			case myNumber(n) => s"$n"
			case mySum(e1, e2) => s"${show(e1)} + ${show(e2)}"
//			case myProd(e1, e2) if (e1.isInstanceOf[myNumber] && e2.isInstanceOf[myNumber]) => s"${show(e1)} * ${show(e2)}"
//			case myProd(e1, e2) if e1.isInstanceOf[myNumber] => s"${show(e1)} * (${show(e2)})"
//			case myProd(e1, e2) if e2.isInstanceOf[myNumber] => s"(${show(e1)}) * ${show(e2)}"
//			case myProd(e1, e2) => s"(${show(e1)}) * (${show(e2)})"
			case myProd(e1, e2) => {
				def maybeShowParentheses(exp: Expr) = exp match {
					case myProd(_, _) => show(exp)
					case myNumber(_) => show(exp)
					case _ => s"(${show(exp)})"
				}
				s"${maybeShowParentheses(e1)} * ${maybeShowParentheses(e2)}"
			}
		}
	}

	val n1 = myNumber(1)
	val n2 = myNumber(2)
	val n3 = myNumber(3)
	val n4 = myNumber(3)

	println(show(myProd(mySum(n1, n2), mySum(myProd(n1, n2), n3))))
	println(show(myProd(mySum(n1, n2), myProd(mySum(n1, n2), mySum(myProd(n1, n2), n3)))))


}
