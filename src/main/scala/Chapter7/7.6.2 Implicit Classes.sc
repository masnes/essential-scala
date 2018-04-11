object IntImplicits {
  implicit class IntOps(int: Int) {
    def yeah(): Unit = times(i => println("Oh yeah!"))

    def times(func: Int => Unit): Unit = {
      for (i <- 0 until int) { func(i) }
    }
  }
}

import IntImplicits._
1.yeah
3.yeah
(-1).yeah

3.times(i => println(s"Look - it's the number $i!"))
3.times(i => println("Oh Yeah!"))
