package Chapter3

case class Counter(count: Int = 0) {
  def inc: Counter = inc()
  def dec: Counter = dec()
  def inc(amount: Int = 1): Counter = copy(this.count + amount)
  def dec(amount: Int = 1): Counter = copy(this.count - amount)

  def adjust(adder: Adder): Counter = copy(adder(this.count))
}
