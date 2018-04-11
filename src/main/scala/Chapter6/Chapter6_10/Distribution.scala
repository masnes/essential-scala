package Chapter6.Chapter6_10

final case class Distribution[A](events: List[(A, Double)]) {
  def normalize: Distribution[A] = {
    val totalWeight = (events map { case (a, p) => p}).sum
    Distribution(events map { case (a, p) => a -> (p / totalWeight)})
  }

  def compact: Distribution[A] = {
    val distinct = (events map { case (a, p) => a }).distinct
    def prob(a: A): Double =
      (events filter { case (x, p) => x == a} map { case (a, p) => p}).sum

    Distribution(distinct map { a => a -> prob(a) })
  }

  def flatMap[B](f: A => Distribution[B]): Distribution[B] =
    Distribution(events flatMap { case (a1, p1) =>
      f(a1).events.map { case (b, p2) => b -> (p1 * p2) }}).compact.normalize


  def map[B](f: A => B): Distribution[B] =
    Distribution(events map { case (a, p) => f(a) -> p } )

}

object Distribution {
  def uniform[A](atoms: List[A]): Distribution[A] = {
    val p = 1.0 / atoms.length
    Distribution(atoms.map(a => a -> p))
  }

  def discrete[A](events: List[(A, Double)]): Distribution[A] =
    Distribution(events).compact.normalize
}
