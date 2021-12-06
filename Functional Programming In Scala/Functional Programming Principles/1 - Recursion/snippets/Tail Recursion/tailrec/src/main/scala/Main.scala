import scala.annotation.tailrec
def gcd(a: Int, b: Int): Int = {
  if b == 0 then a else gcd(b, a % b)
}

// @tailrec // this gives a warning since the implimentation is not tail recursive 
def factorial(n: Int): Int = {
  if n == 0 then 1 else n * factorial(n-1)
}

@tailrec
def factorial_tailrec(n: Int, accumulator: Int = 1): Int = {
  if n == 0 then accumulator else factorial_tailrec(n-1, accumulator * n)
}

@main def test = {
  println(gcd(14, 21))
  println(factorial(4))
  println(factorial(25))
  println(factorial_tailrec(25))
}
