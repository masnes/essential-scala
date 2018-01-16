package Chapter4

sealed trait TrafficLight {
  def next: TrafficLight
}
case object RedLight extends TrafficLight {
  def next: TrafficLight = GreenLight
}
case object GreenLight extends TrafficLight {
  def next: TrafficLight = YellowLight
}
case object YellowLight extends TrafficLight {
  def next: TrafficLight = RedLight
}

case object Sequence {
  def next(trafficLight: TrafficLight): TrafficLight =
    trafficLight match {
      case GreenLight => YellowLight
      case YellowLight => RedLight
      case RedLight => GreenLight
    }
}
