package Chapter3

object Executor {
  def main(args: Array[String]): Unit = {
    val oswald = new Cat("Black", "Milk")
    val henderson = new Cat("Ginger", "Chips")
    val quentin = new Cat("Tabby and white", "Curry")
    println(ChipShop.willServe(oswald))
    println(ChipShop.willServe(henderson))
    println(ChipShop.willServe(quentin))
  }
}
