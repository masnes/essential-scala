package Chapter3

case class Adder(amount: Int) {
  def apply(in: Int): Int = in + amount
}
