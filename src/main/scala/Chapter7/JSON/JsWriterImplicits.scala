package Chapter7.JSON
import Chapter4.{User, Anonymous, Visitor}
import java.util.Date

//final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

//final case class User(id: String, email: String, createdAt: Date = new Date())
//  extends Visitor

object JsWriterImplicits {
  import JsWriterUtilImplicit.JsUtil

  implicit object StringWriter extends JsWriter[String] {
    def write(value: String) = JsString(value)
  }

  implicit object DateWriter extends JsWriter[Date] {
    def write(value: Date) = JsString(value.toString)
  }

  implicit object AnonymousWriter extends JsWriter[Anonymous] {
    def write(value: Anonymous): JsValue = JsObject(Map(
      "id" -> value.id.toJson,
      "createdAt" -> value.createdAt.toJson
    ))
  }

  implicit object UserWriter extends JsWriter[User] {
    def write(value: User): JsValue = JsObject(Map(
      "id" -> value.id.toJson,
      "email" -> value.email.toJson,
      "createdAt" -> value.createdAt.toJson
    ))
  }

  implicit object VisitorWriter extends JsWriter[Visitor] {
    import JsWriterUtilImplicit._
    def write(value: Visitor): JsValue = value match {
      case anon: Anonymous => anon.toJson
      case user: User => user.toJson
    }
  }

}
