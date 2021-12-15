// Sets are another basic abstraction in the Scala collections
// sets are written analagously to a sequence

val fruit = Set("apple", "bananna", "orange")
val s = (1 to 6).toSet

s.map(_ + 2)
fruit.filter(_.startsWith("app"))
s.nonEmpty

// sets are unordered
// sets do not have duplicate items
// the fundamental operation on sets is contains

s.contains(5)

// N-queens problem
// one way to solve this problem is to place a queen on every row
// once we have placed k-1 queens we must place the k'th queen in a column such that 
// it is not threatened by any other queen

def queens(n: Int) =
    def isSafe(col: Int, queens: List[Int]): Boolean =
        !checks(col, 1, queens)

    def checks(col: Int, delta: Int, queens: List[Int]): Boolean = queens match
        case qcol :: others =>
            qcol == col // vertical check
            || (qcol - col).abs == delta // diagonal check
            || checks(col, delta + 1, others)
        case Nil => false

    def placeQueens(k: Int): Set[List[Int]] =
        if k == 0 then Set(List())
        else
            for
                queens <- placeQueens(k - 1)
                col <- 0 until n
                if isSafe(col, queens)
            yield col :: queens
    placeQueens(n)

def printQueens(queens: List[Int]): Unit =
    def printSingleRow(place: Int, shape: Int) =
        println((0 until shape).map(x => if x == place then " Q " else " # ").mkString)
    queens.foreach(x => printSingleRow(x, queens.length))

queens(4)
printQueens(queens(8).head)
