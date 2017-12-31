package Chapter4

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(result: String) extends Calculation
