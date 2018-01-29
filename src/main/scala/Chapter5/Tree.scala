package Chapter5

sealed trait Tree[A] {
  def fold[B](node: (B, B) => B)(leaf: A => B): B =
    this match {
      case Node(left, right) => node(left.fold(node)(leaf), right.fold(node)(leaf))
      case Leaf(value) => leaf(value)
    }
  
}

final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

case object treeToString {
  def string(tree: Tree[String]): String =
    tree.fold[String]((left, right) => left + " " + right)(str => str)
}
