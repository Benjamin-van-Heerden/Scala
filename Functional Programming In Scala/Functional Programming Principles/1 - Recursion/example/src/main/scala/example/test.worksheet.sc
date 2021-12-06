println("hello")


val l = List(3, 4, 5, 6)

l.head

l.tail.tail.tail

def sum(li: List[Int]): Int = {
    if (li.isEmpty) then {
        0
    }
    else {
        li.head + sum(li.tail)
    }
}

sum(l)