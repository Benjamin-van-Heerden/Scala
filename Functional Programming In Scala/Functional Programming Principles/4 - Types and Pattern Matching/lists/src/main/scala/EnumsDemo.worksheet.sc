// think of enums as shorthand for case class hierarchies, i.e. we do not need all the class ... extends Expr qualifiers

enum Expr {
    case VAR(s: String)
    case NUMBER(n: Int)
    case PROD(e1: Expr, e2: Expr)
    case SUM(e1: Expr, e2: Expr)
}

def show(e: Expr): String = e match
    case Expr.VAR(x) =>  x
    case Expr.NUMBER(n) => n.toString
    case Expr.PROD(e1, e2) => s"${showP(e1)} * ${showP(e2)}"
    case Expr.SUM(e1, e2) => s"${show(e1)} + ${show(e2)}"

def showP(e: Expr): String = e match
    case Expr.SUM(e1, e2) => s"(${show(e1)} + ${show(e2)})"
    case _ => show(e)

// enums can also be simple values without parameters

enum Color {
    case Red
    case Green
    case Blue
}

enum DayOfWeek {
    case Mon, Tue, Wed, Thu, Fri, Sat, Sun
}

import DayOfWeek.*

def isWeekend(day: DayOfWeek) = day match
    case Sat | Sun => true
    case _ => false

isWeekend(Mon)
isWeekend(Sat)

// enums can also take parameters and define methods

enum Direction(val dx: Int, val dy: Int) {
    case Right extends Direction(1, 0)
    case Up extends Direction(0, 1)
    case Left extends Direction(-1, 0)
    case Down extends Direction(0, -1)

    def leftTurn = Direction.values((ordinal + 1) % 4)
}

val r = Direction.Right
val u = r.leftTurn
val v = (u.dx, u.dy)