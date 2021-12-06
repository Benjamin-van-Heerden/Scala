type FunSet = Int => Boolean

val s1 = singletonSet(1)
val s2 = singletonSet(3)
val s3 = singletonSet(4)


def contains(s: FunSet, elem: Int): Boolean = s(elem)
def singletonSet(elem: Int): FunSet = (x: Int) => (x == elem)
def union(s: FunSet, t: FunSet): FunSet = (x: Int) => (contains(s, x) || contains(t, x))
def intersect(s: FunSet, t: FunSet): FunSet = (x: Int) => (contains(s, x) && contains(t, x))
def diff(s: FunSet, t: FunSet): FunSet = (x: Int) => (contains(s, x) && !contains(t, x))
def filter(s: FunSet, p: Int => Boolean): FunSet = (x: Int) => (contains(s, x) && p(x))

contains(s1, 1)
contains(s1, 2)

contains(union(s1, s2), 1)
contains(intersect(s1, union(s1, s2)), 1)

contains(diff(union(s1, s2), s1), 2)

-0

val s4 = singletonSet(5)
val s5 = singletonSet(7)
val s6 = singletonSet(1000)

val testSet = union(union(union(union(s1, s2), union(s2, s3)), union(s4, s5)), s6)

val bound = 1000

def toString(s: FunSet): String =
    val xs = for i <- (-bound to bound) if contains(s, i) yield i
    xs.mkString("{", ",", "}")

def printSet(s: FunSet): Unit =
    println(toString(s))

printSet(testSet)

def forall(s: FunSet, p: Int => Boolean): Boolean =
    def iter(a: Int): Boolean =
      if contains(s, a) && !p(a) then
        false
      else if a > bound then
        true
      else
        iter(a + 1)
    iter(-bound)

forall(testSet, x => x > -123)

def exists(s: FunSet, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

exists(testSet, x => x == 4)

def map(s: FunSet, f: Int => Int): FunSet = (x: Int) => exists(s, l => (x == f(l)))

printSet(map(testSet, x => x * x))

printSet(diff(testSet, map(testSet, x => x + 1)))


printSet(filter(testSet, _ < 5))