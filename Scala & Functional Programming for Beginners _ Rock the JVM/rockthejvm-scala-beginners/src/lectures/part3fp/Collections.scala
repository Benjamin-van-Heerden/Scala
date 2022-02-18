package lectures.part3fp

import scala.util.Random

object Collections extends App {
	// Seq
	val aSequence = Seq(1, 3, 4, 2)
	println(aSequence)
	println(aSequence.reverse)
	// apply method retrieves a value
	println(aSequence(2))

	//
	println(aSequence ++ Seq(5, 6, 7))
	//
	println(aSequence.sorted)

	//Ranges

	val aRange: Seq[Int] = 1 to 10
	aRange.foreach(println)

	//Lists
	val aList = List(1, 2, 3)

	val prepended = 42 :: aList
	println(prepended)

	val appended = aList :+ 69
	println(appended)

	//
	val apples5 = List.fill(5)("Apple")
	println(apples5)
	//

	println(aList.mkString("-|-"))

	//
	val numbers = Array(1, 2, 3, 4)
	val threeElements = Array.ofDim[Int](3)

	println(threeElements)
	println(threeElements.foreach(println))

	val threeStrings = Array.ofDim[String](3)
	println(threeStrings.foreach(println))

	//mutation
	numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
	println(numbers.mkString(" "))

	// arrays and sequences

	val numbersSeq: Seq[Int] = numbers // implicit conversion
	println(numbersSeq)

	// vector
	val vector: Vector[Int] = Vector(1, 2, 3)
	println(vector)

	// vectors vs lists
	val maxRuns = 1000
	val maxCapacity = 1000000

	def getWriteTime(collection: Seq[Int]): Double = {
		val r = new Random
		val times = for {
			it <- 1 to maxRuns
		} yield {
			val currentTime = System.nanoTime()
			// operation
			collection.updated(r.nextInt(maxCapacity), 0)
			System.nanoTime() - currentTime
		}

		times.sum*1.0/maxRuns
	}

	val numbersList = (1 to maxCapacity).toList
	val numbersVector = (1 to maxCapacity).toVector

	// keeps reference to tails
	// updating a element in the middle takes long
	println(getWriteTime(numbersList))
	// depth of tree is small
	// needs to replace and entire 32-element chunk
	println(getWriteTime(numbersVector))


}
