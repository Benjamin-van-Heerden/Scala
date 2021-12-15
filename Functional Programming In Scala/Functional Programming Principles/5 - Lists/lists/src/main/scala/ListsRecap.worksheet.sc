// typing
// List[Double]

// construction
val fruits = List("Orange", "Apple", "Pear")
val nums = 1 :: 2 :: Nil

// decomposition
fruits.head
nums.tail
nums.isEmpty

nums match
    case x :: y :: _ => x + y
    case _ => "Other"

// commons methods
val xs = List(1, 2, 5, 8, "this", "that", List(1, 2), 42)

xs.length
xs.last
xs.init
xs.take(5)
xs.drop(5)
xs(1)
xs(4)
// slice(from: Int, upToNotIncluding: Int)
xs.slice(1, 5)
xs.reverse

val n1 = List(1, 2)
val n2 = List(3, 4, 5)

// creating new lists

// concat
val n3 = n1 ++ n2

// updated(pos, new) -> pos must be in range
n3.updated(0, 69)

// finding elements
// indexOf returns -1 if not found
n3.indexOf(4)
n3.contains(5)
n3.contains(123)

def last[T](xs: List[T]): T = xs match
    case List() => throw Error("last of empty list")
    case List(x) => x
    case y :: ys => last(ys)

last(n3)

def init[T](xs: List[T]): List[T] = xs match
    case List() => throw Error("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)

init(n3)

extension [T](xs: List[T])
    def ++ (ys: List[T]): List[T] = xs match
        case Nil => ys
        case x :: xs1 => x :: (xs1 ++ ys)

n3 ++ n1

extension [T](xs: List[T])
    def reverse: List[T] = xs match
        case Nil => Nil
        case y :: ys => ys.reverse ++ List(y)



def removeAt[T](n: Int, xs: List[T]): List[T] = xs match
    case Nil => Nil
    case y :: ys => 
        if n == 0 then ys
        else y :: removeAt(n - 1, ys)

removeAt(1, n3)

val ys = List(List(1, 11), 2, List(3, List(5, 8)), List(42))

def flatten(xs: Any): List[Any] = xs match
    case Nil => Nil
    case y :: ys => flatten(y) ++ flatten(ys)
    case _ => xs :: Nil

flatten(ys)
 



