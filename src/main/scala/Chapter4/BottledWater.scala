package Chapter4

final case class BottledWater(size: Int, source: Source)
sealed trait Source
case object Well extends Source
case object Sprint extends Source
case object Tap extends Source

