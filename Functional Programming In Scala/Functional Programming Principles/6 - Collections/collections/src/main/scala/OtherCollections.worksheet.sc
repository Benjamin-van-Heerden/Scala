// most of the list-like data types in scala are themselves subtypes of "Sequence", which is in turn a subtype of "Iterable"


// Vectors

val nums = Vector(1, 2, 3, -88)
val people = Vector("Alice", "Bob", "Carl")

// The support, methods and operations are the same as for lists, with the exception of ::
// in stead we use :+, so in stead of x :: xs there is
// x +: xs and xs :+ x
// (: always points to the sequence)

nums :+ 2
3 +: nums

nums.map(x => x * 2)

// Ranges -> "Sequences" of evenly spaced integers

// to (inclusive), until (exclusive), by (step value)

1 until 5
1 until 10 by 3

val r = 0 to 6 by 3
r.map(x => x * 2) 

val x = 0 until 6 by 3
x.map(x => x * 2)


// some more sequence operations
val xs = Vector(1, 2, 3, 4, 5)
val ys = Vector(6, 7, 8, 9, 10)

xs.exists(x => x > 5)
xs.exists(x => x > 4)

ys.forall(x => x > 5)


xs.zip(ys)

xs.zip(ys).unzip

xs.flatMap(x => List(x * 2))

// Example: Combinations
val M = 2
val N = 3

(1 to M).flatMap(x => (1 to N).map(y => (x, y)))

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    xs.zip(ys).map((x, y) => x * y).sum

val v1 = Vector(2.0, 3.0, 4.0)
val v2 = Vector(2.0, 3.0, 5.0)

scalarProduct(v1, v2)

// Example: isPrime

def isPrime(n: Int) =
    if n < 2 then false else (2 until n).map(x => n % x).forall(x => x != 0)
    // (2 until n).forall(n % _ != 0) // would be a bit more concise

val i = (1 to 22)
i.map(x => isPrime(x))