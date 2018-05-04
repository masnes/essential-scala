package Chapter7.JSON

trait JsWriter[A] {
  def write(value: A): JsValue
}

object JsWriterImplicits {
  implicit class JsUtil[A](value: A) {
    def toJson(implicit writer: JsWriter[A]): JsValue =
      writer.write(value)
  }
}
