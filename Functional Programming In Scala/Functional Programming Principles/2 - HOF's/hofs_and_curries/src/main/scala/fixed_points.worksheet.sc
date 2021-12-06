def isCloseEnough(x: Double, y: Double, tol: Double = 0.0000001) =
    Math.abs((x - y) / x) < tol

def fixedPoint(f: Double => Double)(firstGuess: Double): Double =
    def iterate(guess: Double): Double = 
        val next = f(guess)
        println(next)
        if isCloseEnough(guess, next) then next
        else iterate(next)
    iterate(firstGuess)

def averageDamp(f: Double => Double)(x: Double): Double = (x + f(x))/2    

def sqrt(x: Double) = 
    fixedPoint(averageDamp(y => x / y))(1.0)

sqrt(2)

def cubeRoot(x: Double) = 
    fixedPoint(averageDamp(y => x / (y * y)))(1.0)

cubeRoot(8)