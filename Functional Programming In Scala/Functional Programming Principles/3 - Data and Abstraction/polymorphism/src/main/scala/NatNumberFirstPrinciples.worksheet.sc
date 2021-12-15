abstract class Nat {
    def isZero: Boolean
    def predecessor: Nat
    def successor: Nat
    def + (that: Nat): Nat
    def - (that: Nat): Nat
    def * (that: Nat): Nat
}

object Zero extends Nat {
    def isZero = true
    def predecessor = throw new NoSuchElementException
    def successor = Succ(this)
    def + (that: Nat) = that
    def - (that: Nat) = if that.isZero then this else throw new NoSuchElementException("Natural numbers are strictly non-negative")
    def * (that: Nat) = this

    override def toString = "Zero"
}

class Succ(n: Nat) extends Nat {
    def isZero = false
    def predecessor = n
    def successor = Succ(this)
    def + (that: Nat) = Succ(n + that)
    def - (that: Nat) = if that.isZero then this else n - that.predecessor

    def * (that: Nat) = that + (this.predecessor * that)

    override def toString = s"Succ($n)"
}

val one = Succ(Zero)
val two = Succ(Succ(Zero))
val three = Succ(two)

one + two

two - one

Zero * two

one * two

two * one

two * three