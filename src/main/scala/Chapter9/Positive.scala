package Chapter9

object Positive {
  def unapply(i: Int): Option[Int] =
    if (i > 0)
      Some(i)
    else
      None

}
