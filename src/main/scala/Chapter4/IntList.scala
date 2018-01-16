package Chapter4

sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(_, tl) => 1 + tl.length
    }
  def product: Int =
    this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.product
    }
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(2*hd, tl.double)
    }
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList

case object ListProcessor {
  def sum(list: IntList): Int =
    list match {
      case End => 0
      case Pair(hd, tl) => hd + sum(tl)
    }
}
