package Chapter4

case class Square(size: Double) extends Rectangular {
  val height: Double = size
  val width: Double = size
}
