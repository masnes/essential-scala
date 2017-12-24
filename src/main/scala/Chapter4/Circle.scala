package Chapter4

case class Circle(radius: Double) extends Shape {
  val sides = 1

  def perimeter: Double = 2 * Math.PI * radius
  def area: Double = Math.PI * radius * radius
}
