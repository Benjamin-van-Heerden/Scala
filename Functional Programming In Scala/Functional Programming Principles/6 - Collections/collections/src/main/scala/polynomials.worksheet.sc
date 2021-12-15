// one way to look at polynomials is as a map from exponents to coefficients
// e.g. x^3 - 2x + 5


class Polynom(nonZeroTerms: Map[Int, Double]) {

    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    val terms = nonZeroTerms.withDefaultValue(0.0)

    def + (other: Polynom) = 
        val all = terms.toList ++ other.terms.toList
        Polynom(all.groupBy(_.head).map((k, v) => (k, v.map(e => e._2).sum)))

    override def toString = 
        val termStrings =
            for (exp, coeff) <- terms.toList.sorted.reverse
            yield
                val exponent = if exp == 0 then "" else s"x^$exp"
                s"$coeff$exponent" 
        if terms.isEmpty then "0"
        else termStrings.mkString(" + ")
    
    def evaluate(x: Double): Double =
        val eval = 
            for (exp, coeff) <- terms.toList yield coeff * Math.pow(x, exp)
        eval.sum
}

val poly = Polynom(0 -> 5, 1 -> -2, 3 -> 1, 4 -> 3)
val otherPoly = Polynom(0 -> 3, 2 -> -6, 4 -> 2)

poly + otherPoly

poly + poly