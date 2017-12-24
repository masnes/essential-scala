package Chapter3

case object Citizen {
  def firstName = "John"
  def lastName = "Doe"
  def name: String = firstName + " " + lastName
}
