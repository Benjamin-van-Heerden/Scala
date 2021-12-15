abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(s: IntSet): IntSet
}

object IntSet {
    def apply(args: Int *) =
        def tempApply(tempArgs: Seq[Int], accum: IntSet = Empty): IntSet =
            if tempArgs.isEmpty then accum else tempApply(tempArgs.tail, accum.incl(tempArgs.head))
        tempApply(args)
}

object Empty extends IntSet {
    def contains(x: Int) = false
    def incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
    def union(other: IntSet) = other

    override def toString = "{}"
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if x < elem then left.contains(x)
      else if x > elem then right.contains(x)
      else true
    
    def incl(x: Int): IntSet =
        if x < elem then NonEmpty(elem, left.incl(x), right)
        else if x > elem then NonEmpty(elem, left, right.incl(x))
        else this
    def union(other: IntSet) = 
        // very recursive solution
        left.union(right).union(other).incl(elem)

    override def toString =
        val xs = for i <- (-100 to 100) if contains(i) yield i
        xs.mkString("{", ",", "}")
}

val test = IntSet(1, 2, 3)

IntSet()