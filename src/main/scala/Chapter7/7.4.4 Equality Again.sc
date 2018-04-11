trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

case class Person(name: String, email: String)

// 7.4.4.1

object Eq {
  def apply[A](v1: A, v2: A)(implicit equal: Equal[A]): Boolean = equal.equal(v1, v2)
}

object EmailEqualImplicit {
  implicit object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email
  }
}

object NameAndEmailEqualImplicit {
  implicit object NameAndEmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email && v1.name == v2.name
  }
}

object Examples {
  def byNameAndEmail: Unit = {
    import NameAndEmailEqualImplicit._
    assert(!Eq(Person("Bob", "support@mail.com"), Person("Jim", "support@mail.com")))
  }

  def byEmail: Unit = {
    import EmailEqualImplicit._
    assert(Eq(Person("Bob", "support@mail.com"), Person("Jim", "support@mail.com")))
  }
}


Examples.byNameAndEmail
Examples.byEmail

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] = instance
}

import NameAndEmailEqualImplicit._
Equal[Person].equal(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
