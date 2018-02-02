package Chapter5.Section5_6

sealed trait Maybe[+A]
final case class Full[A](value: A) extends Maybe[A]
case object Empty extends Maybe[Nothing]
