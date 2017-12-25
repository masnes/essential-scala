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
    val yellowSquare = Square(10, Yellow)
    val pinkCircle = Circle(1.2, Pink)
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
}
