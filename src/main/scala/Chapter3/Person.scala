package Chapter3

case class Person(firstName: String, lastName: String) {
  def name: String = firstName + " " +  lastName
}

object Person {
  def apply(fullName: String): Person = {
    val parts: Array[String] = fullName.split(" ")
    new Person(parts(0), parts(1))
  }
}
