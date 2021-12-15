// suppose we want all the pairs (i, j) of numbers up to N with i + j is prime and 1 <= j < i < N

// - gernerate all possible pairs
// - filter the pairs for which i + j is prime

val N = 5

(1 until N).map(i => (1 until i).map(j => (i, j))).flatten

// useful law:
// xs.flapMap(f) = xs.map(f).flatten

(1 until N).flatMap(i => (1 until i).map(j => (i, j)))

// now we need their sums

(1 until N).flatMap(i => (1 until i).map(j => (i, j))).map(x => (x, x._1 + x._2))

// now we filter those that are prime

def isPrime(n: Int) =
    (2 until n).forall(n % _ != 0)

(1 until N).flatMap(i => (1 until i).map(j => (i, j))).map(x => (x, x._1 + x._2)).filter(x => isPrime(x._2))

// then we return the pairs:

(1 until N).flatMap(i => (1 until i).map(j => (i, j))).map(x => (x, x._1 + x._2)).filter(x => isPrime(x._2)).map(x => x._1)


// this works, but it can make programs difficult to understand
// For-Expressions to the rescue

val nums = Vector(1, 2, 7, 42, 69, -1211)

// yield a new collection consisting of only the elements of the original where the condition is met
// <- is pronounced "taken from"
for v <- nums if v > 6 yield v

// now back to our original example
val M = 7 

for
    i <- 1 until M
    j <- 1 until i
    if isPrime(i + j)
yield (i, j)

// scalar product with for

def scalarProduct(xs: List[Double], ys: List[Double]): Double =
    (for (x, y) <- xs.zip(ys) yield x * y).sum