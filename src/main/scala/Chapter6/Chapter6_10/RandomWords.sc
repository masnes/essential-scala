val subjects = List("Noel", "The cat", "The dog")
val verbs = List("wrote", "chased", "slept on")
val objects = List("the book", "the ball", "the bed")


def verbsFor(subject: String): List[String] =
  subject match {
    case "Noel"    => List("wrote", "chased", "slept")
    case "The cat" => List("meowed at", "chased", "slept on")
    case "The dog" => List("barked at", "chased", "slept on")
  }

def objectsFor(verb: String): List[String] =
  verb match {
    case "wrote"     => List("the book", "the letter", "the code")
    case "chased"    => List("the ball", "the dog", "the cat")
    case "slept on"  => List("the bed", "the mat", "the train")
    case "meowed at" => List("Noel", "the door", "the food cupboard")
    case "barked at" => List("the postman", "the car", "the cat")
  }

def allSentences: List[(String, String, String)] =
  for {
    s <- subjects
    v <- verbsFor(s)
    o <- objectsFor(v)
  } yield (s, v, o)
