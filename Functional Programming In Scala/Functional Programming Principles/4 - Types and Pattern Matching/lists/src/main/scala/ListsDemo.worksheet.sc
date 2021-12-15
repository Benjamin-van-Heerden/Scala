val fruit = List("apples", "oranges", "pears")
val nums = List(1, 3, 2, -1)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()
val mixed = List(1, "s", List(1, 2))

val nums2 = List(3, 4, 5)

nums ++ nums2

def isort(xs: List[Int]): List[Int] = xs match
    case List() => List()
    case y :: ys => insert(y, isort(ys))

def insert(x: Int, xs: List[Int]): List[Int] = xs match
    case List() => List(x)
    case y :: ys => if x < y then x :: xs else y :: insert(x, ys)

isort(nums)