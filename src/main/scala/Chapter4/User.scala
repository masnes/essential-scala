package Chapter4

import java.util.Date

case class User(
 id: String,
 email: String,
 createdAt: Date = new Date()
) extends Visitor
