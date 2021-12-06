class Rational(x: Int, y: Int): 
    require(y > 0, "Denominator must be positive")

    // aux constructor
    def this(x: Int) = this(x, 1)

    val (numer, denom) = reduceFrac(x, y)    

    private def reduceFrac(x: Int, y: Int): (Int, Int) =
        def gcd(a: Int, b: Int): Int =
            if b == 0 then a
            else gcd(b, a % b)

        val g = gcd(x.abs, y)
        (x / g, y / g)

    def add(r: Rational): Rational =
        Rational(r.numer * denom + numer * r.denom, r.denom * denom)

    def + (r: Rational): Rational = this.add(r)

    def mult(r: Rational): Rational = 
        Rational(r.numer * numer, r.denom * denom)
    
    def * (r: Rational): Rational = this.mult(r)

    def neg = Rational(-numer, denom)

    def subtract(r: Rational) = add(r.neg) 

    def - (r: Rational): Rational = this.subtract(r)

    def less(that: Rational): Boolean = numer * that.denom < that.numer * denom

    def < (r: Rational): Boolean = this.less(r)

    def max(that: Rational): Rational =
        if this.less(that) then that else this

    infix def DLESS (r: Rational): Boolean = denom < r.denom  

    override def toString = s"$numer/$denom"
    
end Rational

Rational(1, 2).add(Rational(3, 11))

Rational(1, 2).mult(Rational(3, 11)).add(Rational(2, 22))

val a = Rational(1, 3)
val b = Rational(5, 7)
val c = Rational(3, 2)

b.neg

a.subtract(b).subtract(c)

a.denom

c.max(b)

Rational(5)

a + b

a * b

a < b

a DLESS b
b DLESS c