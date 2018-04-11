import scala.math.Ordering

implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan[Int](Math.abs(_) < Math.abs(_))


assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))
assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))


final case class Rational(numerator: Int, denominator: Int)

implicit val rationalOrdering: Ordering[Rational] = Ordering.fromLessThan[Rational]((a, b) => a.numerator.toDouble / a.denominator.toDouble < b.numerator.toDouble / b.denominator.toDouble)

assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)), List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted)


// Scope for implicits is companion object and then local scope.
// Local scope takes priority.
// If two implicits are defined with the same priority level, a compiler error occurs.
// If using only 1 instance of a class, use define an implicit in the companion object.
// If using more than 1 instance, but there is a sensible default, define the default in a companion object.
// If using more than 1 instance and there are multiple sensible defaults, define them in related companion objects. Then import with import package.companionobject._


// 7.2.5

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units * unitPrice
}

case object Order {
  implicit val totalPriceOrdering: Ordering[Order] = Ordering.fromLessThan[Order](_.totalPrice < _.totalPrice)
}

case object OrderUnitPriceOrdering {
  implicit val unitPriceOrdering: Ordering[Order] =
    Ordering.fromLessThan[Order](_.unitPrice < _.unitPrice)
}

case object OrderUnitsOrdering {
  implicit val unitsOrdering: Ordering[Order] =
    Ordering.fromLessThan[Order](_.units < _.units)
}
