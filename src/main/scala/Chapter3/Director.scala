package Chapter3

case class Director(firstName: String, lastName: String, yearOfBirth: Int) {
  def name: String = {
    firstName + " " + lastName
  }
}

object Director {
  def older(first: Director, second: Director): Director =
    if (first.yearOfBirth <= second.yearOfBirth) first else second
}
