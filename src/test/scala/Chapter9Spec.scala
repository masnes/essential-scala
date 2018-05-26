import org.scalatest._
import Chapter9.Positive

class Chapter9Spec extends FlatSpec {
  assert("No" ==
    (0 match {
      case Positive(_) => "Yes"
      case _ => "No"
    }))

  assert ("No" ==
    (0 match {
      case Positive(_) => "Yes"
      case _ => "No"
    }))
}
