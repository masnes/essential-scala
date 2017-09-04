package Chapter3

class Person(val firstName: String, val lastName: String) { }

object Person {
  def apply(fullName: String): Person = {
    val parts: Array[String] = fullName.split(" ")
    val firstName: String = parts(0)
    val lastName:  String = parts(1)
    new Person(firstName, lastName)
  }
}
