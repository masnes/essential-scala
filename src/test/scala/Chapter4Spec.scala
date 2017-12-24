import Chapter4._
import org.scalatest._

class Chapter4Spec extends FlatSpec {
  "Chapter4.Feline's" should "Always roar, unless cat" in {
    assert(Tiger().sound == "roar")
    assert(Lion("Black", 3).sound == "roar")
    assert(Panther("Black").sound == "roar")
    assert(Cat("red", "mild").sound == "meow")
  }
}
