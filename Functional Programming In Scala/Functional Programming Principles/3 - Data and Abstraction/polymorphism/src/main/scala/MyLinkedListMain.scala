object MyLIstMain extends App {
  val testCons = CONS(1, CONS(2, CONS(3, NIL())))

  println(nth(testCons, 2))


  // 
}

trait LIST[T] {
  def isEmpty: Boolean
  def head: T
  def tail: LIST[T]
}


class NIL[T] extends LIST[T] {
  def isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
}

class CONS[T](val _head: T, val _tail: LIST[T]) extends LIST[T] {
  def isEmpty = false
  def head = _head
  def tail = _tail
}

def singleton[T](elem: T) = CONS[T](elem, NIL[T])

def nth[T](xs: LIST[T], n: Int): T = 
  if xs.isEmpty then throw IndexOutOfBoundsException()
  else if n == 0 then xs.head
  else nth(xs.tail, n - 1)

  
