// 7.3.4.1 Equality

trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

case class Person(name: String, email: String)

object EmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =  v1.email == v2.email
}

object NameAndEmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean = v1.email == v2.email && v1.name == v2.name
}


