object FixedPoints extends App {
    def isCloseEnough(x: Double, y: Double, tol: Double = 0.0001) =
    Math.abs((x - y) / x) < tol

    def fixedPoint(f: Double => Double)(firstGuess: Double): Double =
        def iterate(guess: Double): Double = 
            val next = f(guess)
            println(next)
            if isCloseEnough(guess, next) then next
            else iterate(next)
        iterate(firstGuess)

    def sqrt(x: Double) = 
        fixedPoint(y => x / y)(1.0)

    sqrt(2)
}
