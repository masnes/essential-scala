package Chapter3

class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
  def name: String = {
    firstName + " " + lastName
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Director]

  override def equals(other: Any): Boolean = other match {
    case that: Director =>
      (that canEqual this) &&
        firstName == that.firstName &&
        lastName == that.lastName &&
        yearOfBirth == that.yearOfBirth
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(firstName, lastName, yearOfBirth)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Director {
  def apply(firstName: String, lastName: String, yearOfBirth: Int): Director =
    new Director(firstName, lastName, yearOfBirth)

  def older(first: Director, second: Director): Director =
    if (first.yearOfBirth <= second.yearOfBirth) first else second
}
