val l = List(1, 2, 3) ++ List(4) 

val r = List() ++ List(1)

val k = l.find(_ == 2)

val bool = List(false, true, false, false)

List(true) ++ bool.filter(x => !x).tail

List(1, 2, 3) == List(1, 2, 3)

!(List().length > 0)

def balance(chars: List[Char]): Boolean = {
    if chars.isEmpty then throw new java.util.NoSuchElementException

    def construct_parenthesis_list(
        char_list: List[Char],
        parenthesis_list: List[Char] = List()
    ): List[Char] = {
        if char_list.isEmpty then parenthesis_list
        else {
        if contains_par(char_list.head) then
            construct_parenthesis_list(
            char_list.tail,
            parenthesis_list ++ List(char_list.head)
            )
        else construct_parenthesis_list(char_list.tail, parenthesis_list)
        }
    }

    def contains_par(char: Char) = char == '(' || char == ')'

    def check_balanced(
        par_list: List[Char],
        current_index: Int = 0
    ): Boolean = {
        if par_list.isEmpty then current_index == 0
        else if current_index < 0 then false
        else {
            if par_list.head == '(' then check_balanced(par_list.tail, current_index + 1)
            else check_balanced(par_list.tail, current_index - 1)
        }
    }

    check_balanced(construct_parenthesis_list(chars))
}

balance("(if (zero? x) max (/ 1 x))".toList)

balance(":-)".toList)

balance("())(".toList)


def countChange(money: Int, coins: List[Int]): Int = {
    if money == 0 then 1 else if money < 0 then 0
    else if coins.isEmpty then 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail) 
  }

countChange(4,List(1,2))

countChange(300,List(5,10,20,50,100,200,500))

countChange(301,List(5,10,20,50,100,200,500))