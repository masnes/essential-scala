package Chapter4.JSON

sealed trait JSON {
  def print: String =
    this match {
      case JsNumber(value) => value.toString
      case JsString(value) => quote(value)
      case JsBoolean(value) => value.toString
      case JsNull() => "null"
      case s @ SeqCell(_, _) => "[" + seqPrint(s) + "]"
      case SeqEnd => ""
      case o @ ObjCell(_, _, _) => "{" + objPrint(o) + "}"
      case ObjEnd => ""
    }
  def quote(s: String): String = "\"" + s + "\""
  def seqPrint(seqCell: SeqCell): String =
    seqCell match {
      case SeqCell(head, s @ SeqCell(_, _)) =>
         head.print + ", " + seqPrint(s)
      case SeqCell(head, SeqEnd) =>
        head.print
    }
  def objPrint(objCell: ObjCell): String =
    objCell match {
      case ObjCell(key, value, o @ ObjCell(_, _, _)) =>
        quote(key) + ": " + value.print + ", " + objPrint(o)
      case ObjCell(key, value, ObjEnd) =>
        quote(key) + ": " + value.print
    }
}
final case class JsNumber(value: Double) extends JSON
final case class JsString(value: String) extends JSON
final case class JsBoolean(value: Boolean) extends JSON
final case class JsNull() extends JSON

sealed trait JsSequence extends JSON
final case class SeqCell(head: JSON, tail:JsSequence) extends JsSequence
case object SeqEnd extends JsSequence

sealed trait JsObject extends JSON
final case class ObjCell(key: String, value: JSON, tail: JsObject) extends JsObject
case object ObjEnd extends JsObject
