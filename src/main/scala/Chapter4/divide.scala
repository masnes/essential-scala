package Chapter4

sealed trait DivisionResult

final case class Finite(num: Int) extends DivisionResult
case object Infinite extends DivisionResult

object divide {
  def apply(numerator: Int, denominator: Int): DivisionResult = {
    if (denominator == 0) Infinite else Finite(numerator / denominator)
  }
}

object divisionDescription {
  def apply(divisionResult: DivisionResult): String =
    divisionResult match {
      case Finite(value) => s"It's finite: $value"
      case Infinite      => s"It's infinite"
    }
}
