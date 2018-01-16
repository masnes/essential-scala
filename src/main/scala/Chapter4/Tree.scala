package Chapter4

sealed trait Tree {
  def sum: Int
  def double: Tree =
    this match {
      case Node(left, right) => Node(left.double, right.double)
      case Leaf(element) => Leaf(element * 2)
    }
}
final case class Node(left: Tree, right: Tree) extends Tree {
  def sum: Int = left.sum + right.sum
}
final case class Leaf(element: Int) extends Tree {
  def sum: Int = element
}
