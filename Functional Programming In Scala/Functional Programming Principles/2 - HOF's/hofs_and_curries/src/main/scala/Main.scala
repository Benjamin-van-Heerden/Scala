import scala.annotation.tailrec
@main def hello: Unit = 
  println("hello")
  


def sumInts(a: Int, b: Int): Int = 
  if a > b then 0 else a + sumInts(a + 1, b)

def cube(x: Int): Int = x * x * x

def sumCubes(a: Int, b: Int): Int = 
  if a > b then 0 else cube(a) + sumCubes(a + 1, b)


def fact(x: Int): Int =
  if x == 0 then 1 else x * fact(x - 1)

def sumFacts(a: Int, b: Int): Int = 
  if a > b then 0 else fact(a) + sumFacts(a + 1, b)

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

val testVal = (a: Int, b: Int) => a + b

val anotherTest = (x: Int) => x * x * x * x


// Q: how can we eliminate even more values?
// A: curry the values a and b into the function ant return just that function

def sum_curried(f: Int => Int): (Int, Int) => Int = 
  def sumF(a: Int, b: Int): Int =
    if a > b then 0 else f(a) + sumF(a + 1, b)
  sumF