package Chapter4.CalculatorExercise

sealed trait Expression {
  def eval: Calculation
}
final case class Addition(left: Expression, right: Expression) extends Expression {
  def eval: Calculation =
    (left.eval, right.eval) match {
    case (Failure(reason), _) => Failure(reason)
    case (_, Failure(reason)) => Failure(reason)
    case (Success(a), Success(b)) => Success(a + b)
    }
}

final case class Subtraction(left: Expression, right: Expression) extends Expression {
  def eval: Calculation =
    (left.eval, right.eval) match {
      case (Failure(reason), _) => Failure(reason)
      case (_, Failure(reason)) => Failure(reason)
      case (Success(a), Success(b)) => Success(a - b)
    }
}

final case class Division(left: Expression, right: Expression) extends  Expression {
  def eval: Calculation =
    (left.eval, right.eval) match {
      case (Failure(reason), _) => Failure(reason)
      case (_, Failure(reason)) => Failure(reason)
      case (Success(a), Success(b)) =>
        if (b != 0)
          Success(a / b)
        else
          Failure("Division by zero")
    }
}
final case class SquareRoot(value: Expression) extends Expression {
  def eval: Calculation =
    value.eval match {
      case Failure(reason) => Failure(reason)
      case (Success(result)) =>
        if (result >= 0)
          Success(Math.sqrt(result))
        else
          Failure("Square root of negative number")
    }
}

final case class Number(value: Double) extends Expression {
  def eval: Calculation = Success(value)
}

