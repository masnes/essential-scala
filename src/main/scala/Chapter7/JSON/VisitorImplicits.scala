package Chapter7.JSON
import Chapter4.{User, Anonymous, Visitor}

//final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

//final case class User(id: String, email: String, createdAt: Date = new Date())
//  extends Visitor

object VisitorImplicits {

  implicit object AnonymousWriter extends JsWriter[Anonymous] {
    def write(value: Anonymous): JsValue = JsObject(Map(
      "id" -> JsString(value.id),
      "createdAt" -> JsString(value.createdAt.toString)
    ))
  }

  implicit object UserWriter extends JsWriter[User] {
    def write(value: User): JsValue = JsObject(Map(
      "id" -> JsString(value.id),
      "email" -> JsString(value.email),
      "createdAt" -> JsString(value.createdAt.toString)
    ))
  }

  implicit object VisitorWriter extends JsWriter[Visitor] {
    import JsWriterImplicits._
    def write(value: Visitor): JsValue = value match {
      case anon: Anonymous => anon.toJson
      case user: User => user.toJson
    }
  }

}
