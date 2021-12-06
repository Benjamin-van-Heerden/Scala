import scala.annotation.tailrec

val a = 1
val b = 4

def sumInts(a: Int, b: Int): Int = 
  if a > b then 0 else a + sumInts(a + 1, b)

def cube(x: Int): Int = x * x * x

def sumCubes(a: Int, b: Int): Int = 
  if a > b then 0 else cube(a) + sumCubes(a + 1, b)


def fact(x: Int): Int =
  if x == 0 then 1 else x * fact(x - 1)

def sumFacts(a: Int, b: Int): Int = 
  if a > b then 0 else fact(a) + sumFacts(a + 1, b)

sumInts(a, b)
sumFacts(a, b)
sumCubes(a, b)


sum(identity, a, b)
sum(fact, a, b)
sum(x => x * x, a, b)

// what is the common pattern?

// this is not tailarec!
def sum(f: Int => Int, a: Int, b: Int): Int = 
  if a > b then 0 else f(a) + sum(f, a + 1, b)

// this is
def sum_tailrec(f: Int => Int, a: Int, b: Int) = 
  @tailrec
  def loop(a: Int, acc: Int = 0): Int = 
    if a > b then acc
    else loop(a + 1, f(a) + acc)
  loop(a)

sum_tailrec(fact, a, b)
sum_tailrec(x => x * x, a, b)

val testVal = (a: Int, b: Int) => a + b
testVal(3, 4)

val anotherTest = (x: Int) => x * x * x * x

sum_tailrec(anotherTest, a, b)


// Q: how can we eliminate even more values?
// A: curry the values a and b into the function ant return just that function

def sum_curried(f: Int => Int): (Int, Int) => Int = 
  def sumF(a: Int, b: Int): Int =
    if a > b then 0 else f(a) + sumF(a + 1, b)
  sumF

val cubes = sum_curried(x => x * x * x)

cubes(a, b)

// alternatively
sum_curried(x => x * x * x)(a, b)

def sum_curried_sugar(f: Int => Int)(a: Int, b: Int): Int =
    if a > b then 0 else f(a) + sum_curried_sugar(f)(a + 1, b)

sum_curried_sugar(x => x * x * x)(a, b)