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

}
