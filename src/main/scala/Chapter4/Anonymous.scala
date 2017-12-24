package Chapter4

import java.util.Date

case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor
