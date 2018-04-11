import Chapter6.Chapter6_10._

sealed trait Food
case object Raw extends Food
case object Cooked extends Food

val food: Distribution[Food] = Distribution.discrete(List((Cooked, 0.3), (Raw, 0.7)))

sealed trait Cat
case object Asleep extends Cat
case object Harassing extends Cat

def cat(food: Food): Distribution[Cat] =
  food match {
    case Raw => Distribution.discrete(List((Harassing, 0.8), (Asleep, 0.2)))
    case Cooked => Distribution.discrete(List((Harassing, 0.4), (Asleep, 0.6)))
  }


val foodModel: Distribution[(Food, Cat)] =
  for {
    f <- food
    c <- cat(f)
  } yield (f, c)


// Probability the cat is harassing me
val pHarassing: Double =
  foodModel.events filter {
    case ((_, Harassing), _) => true
    case ((_, Asleep), _) => false
  } map { case (a, p) => p } sum

// Probability the food is cooked given the cat is harassing me
val pCookedGivenHarassing: Option[Double] =
  foodModel.events collectFirst[Double] {
    case ((Cooked, Harassing), p) => p
  } map (_ / pHarassing)
