package Chapter3

object ChipShop {
  def willServe(cat: Cat): Boolean = {
    cat match {
      case Cat(_, "Chips") => true
      case Cat(_, _) => false
    }
  }
}
