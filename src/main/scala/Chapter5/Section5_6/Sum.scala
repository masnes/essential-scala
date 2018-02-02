package Chapter5.Section5_6

sealed trait Sum[+A, +B] {
  def fold[C](error: A => C, success: B => C): C =
    this match {
      case sumFailure(v) => error(v)
      case sumSuccess(v) => success(v)
    }
  def map[C](f: B => C): Sum[A, C] =
    this match {
      case sumFailure(v) => sumFailure(v)
      case sumSuccess(v) => sumSuccess(f(v))
    }
  def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] =
    this match {
      case sumFailure(v) => sumFailure(v)
      case sumSuccess(v) => f(v)
    }
}
final case class sumFailure[A, B](value: A) extends Sum[A, Nothing]
final case class sumSuccess[A, B](value: B) extends Sum[Nothing, B]

