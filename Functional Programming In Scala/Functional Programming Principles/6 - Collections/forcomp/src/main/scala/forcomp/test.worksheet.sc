val s = "bcAcdda"

s.toLowerCase.groupBy(e => e).toMap

for 
    a <- s
yield a -> 1


s.foldRight(List[Char]())((x, y) => x :: y)

val q = List("abcd", "ea")

q.mkString

val dict = List("aftab", "Ababa", "aback", "abaft")

def wordOccurrences(w: String) = w.toLowerCase.groupBy(x => x).map((k, v) => (k, v.length)).toList.sortBy(x => x(0)) 

dict.groupBy(x => wordOccurrences(x))

val aq = List(('a', 2), ('b', 2))

def doSom(l: List[(Char, Int)]): List[List[(Char, Int)]] = 
    val withZeros = l match
        case Nil => List(Nil)
        case x :: xs =>
            for
                j <- (0 to x(1)).toList
                rest <- doSom(xs)
            yield (x(0), j) :: rest
    withZeros.map(x => x.filter(y => y._2 != 0))
    
        
        
aq.take(0)

doSom(aq)

aq.toSet.subsets.map(_.toList).toList

aq.combinations(0).toList

val t = Map('a' -> List(1, 2), 'b' -> List(1, 2))

val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val y = List(('r', 1))

y.map(y => (y._1, -y._2)).concat(x).groupBy(x => x._1).map(x => x._1 -> x._2.map(e => e._2)).map(x => x._1 -> x._2.sum).toList.filter(_._2 != 0).sorted

val xMap = x.toMap
val yMap = y.toMap

yMap.updated('r', 0)

yMap.foldLeft(xMap)((acc, y) => acc.updated(y._1, xMap(y._1) - yMap(y._1))).toList.filter(_._2 != 0).sorted