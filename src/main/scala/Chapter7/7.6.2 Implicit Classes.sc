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

object CaseInsensitiveEqualImplicit {
  implicit object CaseInsensitiveEqual extends Equal[String] {
    def equal(s1: String, s2: String): Boolean =  s1.toLowerCase == s2.toLowerCase()
  }
}

implicit class StringEquality[String](s1: String) {
  def ===(s2: String)(implicit equal: Equal[String]): Boolean =
    equal.equal(s1, s2)
}

import CaseInsensitiveEqualImplicit._
assert("abcd".===("ABCD"))
