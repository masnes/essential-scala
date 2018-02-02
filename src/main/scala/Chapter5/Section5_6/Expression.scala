package Chapter5.Section5_6

sealed trait Expression {
  def eval: Sum[String, Double] =
    this match {
      case Addition(left, right) =>  lift2(left, right, (l, r) => sumSuccess(l + r))
      case Subtraction(left, right) => lift2(left, right, (l, r) => sumSuccess(l - r))
      case Division(left, right) => lift2(left, right,
        (l, r) => if (r != 0) sumSuccess(l / r) else sumFailure("Division by zero"))
      case SquareRoot(value) => lift(value,
        v => if (v >= 0)
            sumSuccess(math.sqrt(v))
          else sumFailure("Square root of negative number"))
      case Number(value) => sumSuccess(value)
    }

  def lift(v: Expression, f: Double => Sum[String, Double]): Sum[String, Double] =
    v.eval flatMap {value => f(value)}
  def lift2(l: Expression, r: Expression,
            f: (Double, Double) => Sum[String, Double]): Sum[String, Double] =
    l.eval flatMap {left =>
      r.eval flatMap { right =>
        f(left, right) }}

}
final case class Addition(left: Expression, right: Expression) extends Expression { }
final case class Subtraction(left: Expression, right: Expression) extends Expression { }
final case class Division(left: Expression, right: Expression) extends Expression { }
final case class SquareRoot(value: Expression) extends Expression { }
final case class Number(value: Double) extends Expression { }
