package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = c match {
    case `r` | 0 => 1
    case _ => pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def go(rest: List[Char], count: Int): Boolean = rest match {
      case _ if count < 0 => false
      case '(' :: t => go(t, count + 1)
      case ')' :: t => go(t, count - 1)
      case _ :: t => go(t, count)
      case Nil => count == 0
    }
    go(chars, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = coins match {
    case Nil => 0
    case h :: t if h > money => countChange(money, t)
    case h :: t if h == money => 1 + countChange(money, t)
    case h :: t => countChange(money - h, coins) + countChange(money, t)
  }
}
