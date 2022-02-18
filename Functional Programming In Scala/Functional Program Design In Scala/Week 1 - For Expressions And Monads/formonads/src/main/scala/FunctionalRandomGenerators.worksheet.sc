trait Generator[+T] {
    def generate(): T
}

val integers = new Generator[Int] {
    val rand = java.util.Random()
    def generate() = rand.nextInt()
}

integers.generate()

val booleans = new Generator[Boolean] {
    def generate() = integers.generate() > 0
}

booleans.generate()

val pairs = new Generator[(Int, Int)] {
    def generate() = (integers.generate(), integers.generate())
}

pairs.generate()

// can we avoid the "Generator" boilerplate?

extension [T, S](g: Generator[T])
    def map(f: T => S) = new Generator[S] {
        def generate() = f(g.generate())
    }

    def flatMap(f: T => Generator[S]) = new Generator[S] {
        def generate() = f(g.generate()).generate()
    }

val bools = for x <- integers yield x > x

bools.generate()

def single[T](x: T): Generator[T] = new Generator[T] {
    def generate() = x
}

single(3).generate()

def range(lo: Int, hi: Int): Generator[Int] =
    for x <- integers yield lo + x.abs % (hi - lo)

range(10, 100).generate()

def oneOf[T](xs: T*): Generator[T] = 
    for idx <- range(0, xs.length) yield xs(idx)

oneOf(1, 2, 3, "s", 3.0).generate()

// list generator

def lists: Generator[List[Int]] =
    for
        kind <- range(0, 10)
        list <- if kind == 0 then emptyLists else nonEmptyLists
    yield list

def emptyLists = single(Nil)

def nonEmptyLists =
    for
        head <- integers
        tail <- lists
    yield head :: tail

lists.generate()

enum Tree {
    case Inner(left: Tree, right: Tree)
    case Leaf(x: Int) 
}

def treeGen: Generator[Tree] =
    for
        isLeaf <- booleans
        tree <- if isLeaf then leaf else inners
    yield tree

def leaf = 
    for x <- integers yield Tree.Leaf(x)

def inners = 
    for
        left <- treeGen
        right <- treeGen
    yield Tree.Inner(left, right)

treeGen.generate()

// automated random testing

def test[T](g: Generator[T], numTimes: Int = 100)(test: T => Boolean) =
    for i <- 0 until numTimes do
        val value = g.generate()
        assert(test(value), s"test failed for $value")
    println(s"passed $numTimes tests")

def pairGen[T](g: Generator[T], f: Generator[T]): Generator[(T, T)] =
    for
        x <- g
        y <- f
    yield (x, y)

test(pairGen(lists, lists)) {
    (xs, ys) => (xs ++ ys).length >= xs.length
}