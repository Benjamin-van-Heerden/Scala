// reduce(Left) - inserts a binary(and associative) operator between adjacent elements of a list and evaluates the obtained expression

val l = List(1, 2, 3, 4, 5, 6)

l.reduce((x, y) => x + y)
l.reduce(_ + _)

l.reduce((x, y) => x * y)

l.reduceLeft((x, y) => x + y)

def REVERSE[T](xs: List[T]): List[T] = xs.foldLeft(List[T]())((x, y) => y :: x)

REVERSE(l)

// for the fold operation the most critical part is the binary operand e.g. (x, xs) => x + xs, here 
// x is the "new" element and xs assumes the value of the zero from the previous step (it is like a running accumulator)

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    xs.foldRight(List[U]())((y, ys) => f(y) :: ys)

def lengthFun[T](xs: List[T]): Int =
    xs.foldRight(0)((y, n) => n + 1)

mapFun(l, x => x * x)
lengthFun(l)
