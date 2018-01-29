package Chapter5

sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case LLPair(hd, tl) => f(hd, tl.fold(end, f))
    }

  def length: Int =
    this match {
      case LLPair(_, tl) => 1 + tl.length
      case End() => 0
    }
  def contains(content: A): Boolean =
    this match {
      case LLPair(hd, tl) => hd == content || tl.contains(content)
      case End() => false
    }

  def apply(n: Int): Result[A] = {
    if (n < 0)
      Failure("Negative index provided")
    else
      this match {
        case LLPair(hd, tl) => if (n == 0) Success(hd) else tl(n-1)
        case End() => Failure("Index out of bounds")
      }
  }
}

final case class LLPair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]