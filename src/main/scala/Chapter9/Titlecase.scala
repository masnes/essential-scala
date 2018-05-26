package Chapter9

object Titlecase {
  def unapply(arg: String): Option[String] =
    Some(
      arg.split(' ').toList.map(
        (s: String) =>  s.splitAt(1) match {
          case (f: String, l: String) => f.toUpperCase + l
        }
      ).mkString((" "))
    )
}
