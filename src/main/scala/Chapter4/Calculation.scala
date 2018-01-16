package Chapter4

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(result: String) extends Calculation

case object CalculatorObject {
  def +(calculation: Calculation, operand: Int): Calculation = {
    calculation match {
      case Success(result) => Success(result + operand)
      case Failure(result) => Failure(result)
    }
  }
  def -(calculation: Calculation, operand: Int): Calculation = {
    calculation match {
      case Success(result) => Success(result - operand)
      case Failure(result) => Failure(result)
    }
  }
  def /(calculation: Calculation, operand: Int): Calculation = {
    (calculation, operand) match {
      case (Failure(result), _) => Failure(result)
      case (_, 0) => Failure("Division by zero")
      case (Success(result), _) => Success(result / operand)
    }
  }
}
