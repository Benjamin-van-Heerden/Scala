// NB Mapping
extension [T](xs: List[T])
    // the actual implimentation of map is more compicated as it is tail recursive 
    // and works for arbitraty collections
    def map[U](f: T => U): List[U] = xs match
        case Nil => Nil
        case x :: xs => f(x) :: xs.map(f)

val n1 = List(1, 2)
val n2 = List(3, 4, 5)
val n3 = n1 ++ n2

val n4 = n3 ++ n1

n4.map(x => x * 2)

n4.map(x => x > 3)

n4.map(x => x * x)

extension [T](xs: List[T])
    // again the actual implimentation is a bit more compliated
    def filter(f: T => Boolean): List[T] = xs match
        case Nil => Nil
        case y :: ys => if f(y) then y :: ys.filter(f) else ys.filter(f)

n4.filter(x => x > 2)

// variations of filter
n4.filterNot(x => x > 2)

n4.partition(x => x > 2)

n4.takeWhile(x => x < 3)

n4.dropWhile(x => x < 3)

n4.span(x => x < 3)

n4.partition(x => x % 2 == 0)
n4.span(x => x % 2 == 0)

def pack[T](xs: List[T]): List[List[T]] = xs match
    case Nil => Nil
    case x :: xs1 => xs.takeWhile(y => y == x) :: pack(xs1.dropWhile(y => y == x))

val elems = List("a", "a", "a", "b", "c", "c", "a")

pack(elems)


def encode[T](xs: List[T]): List[Tuple] = 
    pack(xs).map(x => (x.head, x.length))

encode(elems)