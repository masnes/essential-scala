import Chapter5.Section5_6.{sumFailure, sumSuccess, _}
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

  "Chapter5.LinkedList" should "Be doublable, incrementable, and divisible via Map" in {
    val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
    val double: (Int => Int) = _ * 2
    val doubled: LinkedList[Int] = Pair(2, Pair(4, Pair(6, End())))
    val increment: (Int => Int) = _ + 1
    val incremented: LinkedList[Int] = Pair(2, Pair(3, Pair(4, End())))
    val divideByThree: (Int => Int) = _ / 3
    val divided: LinkedList[Int] = Pair(0, Pair(0, Pair(1, End())))
    assert(list.map(double) == doubled)
    assert(list.map(increment) == incremented)
    assert(list.map(divideByThree) == divided)
  }

  "Chapter5.Section5_6.Expression" should "Use an eval method with Sum" in {
    assert(Addition(Number(1), Number(2)).eval == sumSuccess(3))
    assert(SquareRoot(Number(-1)).eval == sumFailure("Square root of negative number"))
    assert(Division(Number(4), Number(0)).eval == sumFailure("Division by zero"))
    assert(Division(Addition(Subtraction(Number(8), Number(6)), Number(2)), Number(2)).eval == sumSuccess(2.0))
  }
}
