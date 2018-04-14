object IntImplicits {
  implicit class IntOps[A](n: Int) {
    def yeah(): Unit = times(_ => println("Oh yeah!"))

    def times(func: Int => Unit): Unit = {
      for (i <- 0 until n) {
        func(i)
      }
    }

  }
}

import IntImplicits._
1.yeah()
3.yeah()
(-1).yeah()

3.times(i => println(s"Look - it's the number $i!"))
3.times(i => println("Oh Yeah!"))


trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] =
    instance

  implicit class ToEqual[A](in: A) {
    def ===(other: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(in, other)
  }
}

implicit val caseInsensitiveEquals: Equal[String] = (s1: String, s2: String) => s1.toLowerCase() == s2.toLowerCase()

import Equal._
assert("abcd".===("ABCD"))
