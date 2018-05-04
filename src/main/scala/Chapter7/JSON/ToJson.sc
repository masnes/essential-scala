import java.util.Date

import Chapter4.{Anonymous, User, Visitor}
import Chapter7.JSON.JsWriterImplicits.VisitorWriter
import Chapter7.JSON._

val visitors: Seq[Visitor] =
  Seq(
    Anonymous("001", new Date),
    User("003", "dave@xample.com", new Date)
  )

import Chapter7.JSON.JsWriterUtilImplicit._
import Chapter7.JSON.JsWriterImplicits.VisitorWriter
val visitorsJson = visitors.map(visitor => visitor.toJson)

val visitorJsonStrings = visitorsJson.map(_.stringify)
