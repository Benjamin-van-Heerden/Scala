import scala.quoted.Expr
trait Expr
case class NUMBER(n: Int) extends Expr
case class SUM(e1: Expr, e2: Expr) extends Expr
case class PROD(e1: Expr, e2: Expr) extends Expr
case class VAR(v: String) extends Expr
// case class Test(n: Int) extends Expr


def eval(e: Expr): Int = e match
    case NUMBER(n) => n
    case SUM(e1, e2) => eval(e1) + eval(e2)
    case PROD(e1, e2) => eval(e1) * eval(e2)
    case _ => throw Error(s"Cannot Convert Expression {$e} to Integer")

def show(e: Expr): String = e match
    case NUMBER(n) => s"$n"
    case SUM(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case PROD(e1, e2) => s"${showP(e1)} * ${showP(e2)}"
    case VAR(v) => v
    case _ => throw Error(s"Unknown Expression: $e")

def showP(e: Expr): String = e match
    case e: SUM => s"(${show(e)})"
    case _ => show(e)

val one = NUMBER(1)
val two = NUMBER(2)
val sum = SUM(one, two)
val anotherSum = SUM(sum, NUMBER(3))

eval(sum)

// eval(SUM(one, Test(1)))

show(sum)

show(anotherSum)

val prod = PROD(anotherSum, sum)
eval(prod)

show(prod)

val p1 = PROD(NUMBER(1), SUM(NUMBER(1), NUMBER(2)))
val p2 = PROD(SUM(NUMBER(1), NUMBER(2)), NUMBER(1))
val p3 = PROD(SUM(NUMBER(1), VAR("x")), VAR("y"))
val p4 = PROD(p1, p3)
val p5 = PROD(SUM(VAR("z"), VAR("w")), SUM(p4, two))


show(p1)
show(p2)
show(p3)
show(p4)
show(p5)
