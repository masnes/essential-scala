import Chapter5._
import org.scalatest._

class Chapter5Spec extends FlatSpec {
  "Chapter5.LinkedList" should "Implement a (generic) length method recursively." in {
    val example = Pair(1, Pair(2, Pair(3, End())))
    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End().length == 0)
  }

  "Chapter5.LinkedList" should "Implement a (generic) contains method recursively." in {
    val example = Pair(1, Pair(2, Pair(3, End())))
    assert(example.contains(3))
    assert(!example.contains(4))
    assert(!End().contains(0))
  }

  "Chapter5.LinkedList" should "Implement a (generic) apply method that retrieves the nth item of the list." in {
    val example = Pair(1, Pair(2, Pair(3, End())))
    assert(example(0) == Success(1))
    assert(example(1) == Success(2))
    assert(example(2) == Success(3))
    assert(example(3) == Failure("Index out of bounds"))
    assert(example(-1) == Failure("Negative index provided"))
  }

  "Chapter5.Tree" should "Print itself with a string method using fold" in {
    val tree: Tree[String] =
      Node(
        Node(Leaf("To"), Leaf("iterate")),
        Node(
          Node(Leaf("is"), Leaf("human,")),
          Node(
            Leaf("to"),
            Node(Leaf("recurse"), Leaf("divine")))))
    assert(treeToString.string(tree) == "To iterate is human, to recurse divine")
  }
}
