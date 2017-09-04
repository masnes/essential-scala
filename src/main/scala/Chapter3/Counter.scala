package Chapter3

class Counter(var count: Int) {
  def inc: Counter = inc()
  def dec: Counter = dec()
  def inc(amount: Int = 1) = new Counter(this.count + amount)
  def dec(amount: Int = 1) = new Counter(this.count - amount)

  def adjust(adder: Adder) = new Counter(adder(this.count))

  def canEqual(other: Any): Boolean = other.isInstanceOf[Counter]

  override def equals(other: Any): Boolean = other match {
    case that: Counter =>
      (that canEqual this) &&
        count == that.count
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(count)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
