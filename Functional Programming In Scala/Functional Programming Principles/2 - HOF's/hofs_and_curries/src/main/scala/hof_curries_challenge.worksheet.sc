val a = 1

val b = 4

def sum(f: Int => Int)(a: Int, b: Int): Int =
    if a > b then 0 else f(a) + sum(f)(a + 1, b)

def product(f: Int => Int)(a: Int, b: Int): Int = 
    if a > b then 1 else f(a) * product(f)(a + 1, b)

def fact(n: Int) = product(x => x)(1, n)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, id: Int)(a: Int, b: Int) =
    def recur(a: Int): Int = 
        if a > b then id else combine(f(a), recur(a + 1))
    recur(a)

def sumMap(f: Int => Int) = mapReduce(f, (x, y) => x + y, 0)

mapReduce(x => x, (x, y) => x * y, 1)(1, 4)
fact(4)
mapReduce(x => x, (x, y) => x + y, 0)(1, 4)
sum(x => x)(1, 4)