package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App{
	@tailrec
	def tailRecFact(n: Int, accumulator: Int = 1): Int = {
		if (n <= 1) accumulator
		else tailRecFact(n-1, n*accumulator)
	}

	//val fact10 = tailRecFact(10, 1) // have to explicitly pass 1 for the base value

	val fact10 = tailRecFact(10)

	def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture")
	// either pass in every leading argument OR we can name the arguments
	savePicture(width = 800, format = "bitmap") //etc..
}
