import org.scalatest._
import Chapter9._

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

  assert(
    "Sir Lord Doctor David Gurnell" ==
      ("sir lord doctor david gurnell" match {
        case Titlecase(str) => str
      })
  )
}
