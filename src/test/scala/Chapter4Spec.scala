import Chapter4.CalculatorExercise._
import Chapter4.JSON._
import Chapter4._
import org.scalatest._

class Chapter4Spec extends FlatSpec {
  "Chapter4.Feline's" should "Always roar, unless cat" in {
    assert(Tiger().sound == "roar")
    assert(Lion("Black", 3).sound == "roar")
    assert(Panther("Black").sound == "roar")
    assert(Cat("red", "mild").sound == "meow")
  }

  "Chapter4.Color" should "Have an apply method that returns the shape name, dimension, and color" in {
    val yellowSquare = Square(10, YellowColor)
    val pinkCircle = Circle(1.2, PinkColor)
    val lightCustomRectangle = Rectangle(2, 2, CustomColor(64, 63, 64))
    val darkCustomRectangle = Rectangle(2, 2, CustomColor(63, 63, 63))
    assert(Draw(yellowSquare) == "A yellow square of size 10.0cm")
    assert(Draw(pinkCircle) == "A pink circle of radius 1.2cm")
    assert(Draw(lightCustomRectangle) ==
      "A light rectangle of width 2.0cm and height 2.0cm")
    assert(Draw(darkCustomRectangle) ==
      "A dark rectangle of width 2.0cm and height 2.0cm")
  }

  "Chapter4.divide" should "Produce a Chapter4.Infinite result if divide by zero, else a Chapter4.Finite result of the division" in {
    assert(divide(1, 0) == Infinite)
    assert(divide(4, 2) == Finite(2))
    assert(divide(1, 2) == Finite(0))
    assert(divisionDescription(divide(1, 2)) == "It's finite: 0")
    assert(divisionDescription(divide(4, 2)) == "It's finite: 2")
    assert(divisionDescription(divide(1, 0)) == "It's infinite")
  }

  "Chapter4.Calculator" should "Include + and - methods that work as standard addition." in {
    assert(CalculatorObject.+(Chapter4.Success(1), 1) == Chapter4.Success(2))
    assert(CalculatorObject.-(Chapter4.Success(1), 1) == Chapter4.Success(0))
    assert(CalculatorObject.+(Chapter4.Failure("Badness"), 1) == Chapter4.Failure("Badness"))
    assert(CalculatorObject./(Chapter4.Success(4), 2) == Chapter4.Success(2))
    assert(CalculatorObject./(Chapter4.Success(4), 0) == Chapter4.Failure("Division by zero"))
    assert(CalculatorObject./(Chapter4.Failure("Badness"), 0) == Chapter4.Failure("Badness"))
  }

  "Chapter4.IntList" should "Build and sum in a limited recursive fashion with a base case." in {
    val example = Pair(1, Pair(2, Pair(3, End)))
    assert(ListProcessor.sum(example) == 6)
    assert(ListProcessor.sum(example.tail) == 5)
    assert(ListProcessor.sum(End) == 0)
    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End.length == 0)
    assert(example.product == 6)
    assert(example.tail.product == 6)
    assert(End.product == 1)
    assert(example.double == Pair(2, Pair(4, Pair(6, End))))
    assert(example.tail.double == Pair(4, Pair(6, End)))
    assert(End.double == End)
  }

  "Chapter4.Tree" should "implement sum and double using polymorphism and pattern matching" in {
    val example = Node(Node(Leaf(5), Leaf(2)), Leaf(6))
    assert(example.sum == 13)
    assert(example.double == Node(Node(Leaf(10), Leaf(4)), Leaf(12)))
  }

  "Chapter4.Calculator" should "Work as a normal calculator, but consider that a calculation may fail" in {
    assert(Addition(SquareRoot(Number(-1.0)), Number(2.0)).eval
      == Chapter4.CalculatorExercise.Failure("Square root of negative number"))
    assert(Addition(SquareRoot(Number(-1.0)), Number(2.0)).eval ==
      Chapter4.CalculatorExercise.Failure("Square root of negative number"))
    assert(Addition(SquareRoot(Number(4.0)), Number(2.0)).eval == Chapter4.CalculatorExercise.Success(4.0))
    assert(Division(Number(4), Number(0)).eval == Chapter4.CalculatorExercise.Failure("Division by zero"))
  }

  "Chapter4.JSON" should "Print json results from an algebraic representation." in {
    val shortSeq = SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqEnd)))
    assert(shortSeq.print == "[\"a string\", 1.0, true]")

    val longerSeq =
      ObjCell(
        "a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber(3.0), SeqEnd))),
        ObjCell(
          "b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell(JsString("c"), SeqEnd))),
          ObjCell(
            "c", ObjCell("doh", JsBoolean(true),
              ObjCell("ray", JsBoolean(false),
                ObjCell("me", JsNumber(1.0), ObjEnd))),
            ObjEnd
          )
        )
      )
    assert(longerSeq.print == "{\"a\": [1.0, 2.0, 3.0], \"b\": [\"a\", \"b\", \"c\"], \"c\": {\"doh\": true, \"ray\": false, \"me\": 1.0}}")
  }
}
