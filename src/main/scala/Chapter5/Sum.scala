package Chapter5

sealed trait Sum[A, B] {
  def fold[C](error: A => C, success: B => C): C =
    this match {
      case sumFailure(a) => error(a)
      case sumSuccess(b) => success(b)
    }
  def map[C](fn: B => C): Sum[A, C] =
    this match {
      case sumFailure(v) => sumFailure(v)
      case sumSuccess(v) => sumSuccess(fn(v))

    }
  def flatMap[C](fn: B => Sum[A, C]): Sum[A, C] =
    this match {
      case sumFailure(v) => sumFailure(v)
      case sumSuccess(v) => fn(v)
    }
}
final case class sumFailure[A, B](value: A) extends Sum[A, B]
final case class sumSuccess[A, B](value: B) extends Sum[A, B]
