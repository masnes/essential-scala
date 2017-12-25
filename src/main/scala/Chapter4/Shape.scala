package Chapter4

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color
}

final case class Circle(radius: Double, color: Color) extends Shape {
  val sides = 1

  def perimeter: Double = 2 * Math.PI * radius
  def area: Double = Math.PI * radius * radius
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides: Int = 4
  val perimeter: Double = 2*width + 2*height
  val area: Double = width * height
}

final case class Rectangle(width: Double, height: Double, color: Color)
  extends Rectangular { }

final case class Square(size: Double, color: Color) extends Rectangular {
  val height: Double = size
  val width: Double = size
}

object Draw {
  def apply(color: Color): String = {
    color match {
      case Red => "red"
      case Pink => "pink"
      case Yellow => "yellow"
      case CustomColor(_, _, _) => if (color.isLight) "light" else "dark"
    }
  }

  def apply(shape: Shape): String = {
    shape match {
      case Rectangle(width, height, color) =>
        s"A ${Draw(color)} rectangle of width ${width}cm and height ${height}cm"
      case Square(size, color) =>
        s"A ${Draw(color)} square of size ${size}cm"
      case Circle(radius, color) =>
        s"A ${Draw(color)} circle of radius ${radius}cm"
    }
  }
}
