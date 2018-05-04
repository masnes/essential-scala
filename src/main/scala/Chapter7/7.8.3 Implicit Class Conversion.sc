object IntImplicits {
  class IntOps(n: Int) {
    implicit def yeah(): Unit = println("Oh yeah!")

    implicit def times(func: Int => Unit): Unit = for (i <- 0 until n) {
      func(i)
    }

  }
  implicit def intToIntOpt(in: Int): IntOps = new IntOps(in)

}

import IntImplicits._
1.yeah()
2.times(int => println(s"Oh $int"))
