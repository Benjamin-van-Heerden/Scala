extension [T](xs: List[T])
    def splitAt(n: Int) = (xs.take(n), xs.drop(n))

def msort(xs: List[Int]): List[Int] = 
    val n = xs.length / 2
    if n == 0 then xs
    else
        def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match
            case (Nil, ys) => ys
            case (xs, Nil) => xs
            case (x :: xs1, y :: ys1) =>
                if x < y then x :: merge(xs1, ys)
                else y :: merge(xs, ys1) 
        val (fst, snd) = xs.splitAt(n)
        merge(msort(fst), msort(snd))

val n1 = List(1, 2)
val n2 = List(3, 4, 5)
val n3 = n1 ++ n2

val n4 = n3 ++ n1

msort(n4)

def msortPoly[T](xs: List[T])(lt: (T, T) => Boolean): List[T] =
    val n = xs.length / 2
    if n == 0 then xs
    else
        def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match
            case (Nil, ys) => ys
            case (xs, Nil) => xs
            case (x :: xs1, y :: ys1) =>
                if lt(x, y) then x :: merge(xs1, ys)
                else y :: merge(xs, ys1) 
        val (fst, snd) = xs.splitAt(n)
        merge(msortPoly(fst)(lt), msortPoly(snd)(lt))

msortPoly(n4)((x, y) => x < y)

val fruit = List("apple", "oranges", "pear")

// by length
msortPoly(fruit)((x, y) => x.length < y.length)
// by lexical rank
msortPoly(fruit)((x, y) => x.compareTo(y) < 0)