def sqrt(x: Double): Double = {
  def sqrtIter(guess: Double): Double = {
    if isGoodGuess(guess) then guess
    else  
      // println(s"Current est: $guess")
      sqrtIter(improve(guess))
  }

  def isGoodGuess(guess: Double, error: Double = 10e-7): Boolean = {
    Math.abs(guess * guess - x) < error  
  }

  def improve(guess: Double): Double = {
    // take the mean of the guess and the quotient of the value divided by the guess as per the 
    (guess + x / guess) / 2
  }

  sqrtIter(1)
}

def gcd(a: Int, b: Int): Int = {
  if b == 0 then a else gcd(b, a % b)
}

@main def test = {
  println(sqrt(2))
  println(gcd(14, 21))
}