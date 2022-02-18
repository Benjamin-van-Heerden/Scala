package quickcheck

import org.scalacheck.*
import Arbitrary.*
import Gen.*
import Prop.forAll
import scala.math.min

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap:
  lazy val genHeap: Gen[H] = oneOf(
    const(empty),
    for
      x <- arbitrary[Int]
      h <- oneOf(const(empty), genHeap)
    yield insert(x, h) 
  )
  given Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if isEmpty(h) then 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { (a: Int) =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("minOfTwo") = forAll { (a: Int, b: Int) =>
    val h = insert(a, insert(b, empty))
    findMin(h) == min(a, b)
  }

  property("insertRemoveOne") = forAll { (a: Int) => 
    val h = insert(a, empty)
    isEmpty(deleteMin(h))
  }

  property("sortByMinima") = forAll { (h: H) => 
    def reduceToEmpty(heap: H, acc: List[A] = List()): List[A] = 
      if isEmpty(heap) then acc
      else reduceToEmpty(deleteMin(heap), acc ++ List(findMin(heap)))
    
    val l = reduceToEmpty(h)
    l == l.sorted
  }

  property("meldedMin") = forAll { (h1: H, h2: H) =>
    if isEmpty(h1) || isEmpty(h2) then true
    else
      val m1 = findMin(h1)
      val m2 = findMin(h2)
      findMin(meld(h1, h2)) == min(m1, m2)
  }

  property("deleteMinTwice") = forAll { (a: Int, b: Int, c: Int) =>
    val maxABC = List(a, b, c).max
    val h = insert(a, insert(b, insert(c, empty)))
    val hDelete = deleteMin(deleteMin(h))
    findMin(hDelete) == maxABC
  }

