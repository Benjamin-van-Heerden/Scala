package recfun

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do print(s"${pascal(col, row)} ")
      println()

  /** Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if c == 0 || c == r then 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /** Exercise 2
    */
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

  /** Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    if money == 0 then 1 else if money < 0 then 0
    else if coins.isEmpty then 0
    else countChange(money - coins.head, coins) + countChange(money, coins.tail) 
  }
