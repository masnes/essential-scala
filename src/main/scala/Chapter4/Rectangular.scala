package Chapter4

trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides: Int = 4
  val perimeter: Double = 2*width + 2*height
  val area: Double = width * height
}
