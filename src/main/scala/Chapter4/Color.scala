package Chapter4

sealed trait Color {
  def red: Byte
  def green: Byte
  def blue: Byte

  def isLight: Boolean = red > 63 || green > 63 || blue > 63
  def isDark: Boolean = !isLight

}

case object RedColor extends Color {
  val red: Byte = Byte.MaxValue
  val green: Byte = 0
  val blue: Byte = 0
}

case object YellowColor extends Color {
  val red: Byte = Byte.MaxValue
  val green: Byte = Byte.MaxValue
  val blue: Byte = 0
}

case object PinkColor extends Color {
  val red: Byte = Byte.MaxValue
  val green: Byte = (Byte.MaxValue / 2).toByte
  val blue: Byte = (Byte.MaxValue / 2).toByte
}

case object GreenColor extends Color {
  val red: Byte = 0
  val green: Byte = Byte.MaxValue
  val blue: Byte = 0
}

case class CustomColor(red: Byte, green: Byte, blue: Byte) extends Color { }
