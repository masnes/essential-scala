package Chapter4

final case class BottledWater(size: Int, source: Source
sealed trait Source
final case object Well extends Source
final case object Sprint extends Source
final case object Tap extends Source

