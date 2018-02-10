import Chapter6._

import java.util.NoSuchElementException

import org.scalatest._

class Chapter6Spec extends FlatSpec {
  "answers to 6.1.9.1" should "be accurate" in {
    val seq = Seq(1, 2, 3)
    val length = seq.length
    val size = seq.size
    assert(length == size) // Seq.length is a synonym of Seq.size
    // head handles the empty seq by throwing an exception
    // headOption handles false case using the option monad
    assert(seq.head == seq.headOption.get)
    assert(
      try {
        Seq().head
        false
      } catch {
        case _: NoSuchElementException => true
      }
    )
    assert(Seq().headOption.isEmpty)
    assert(seq.mkString == "123") // Display the elements as a string
    assert(None.isEmpty) // Test if an option is defined
    assert(Some(1).isDefined) // Test if an option is defined
  }

  "answers to 6.1.9.2" should "be accurate" in {
    val animals = Seq("cat", "dog", "penguin")
    "tyrannnosaurus" +: animals :+ "mouse"
    val anyList = 2 +: animals
    print(anyList.getClass)
  }

    import Chapter6_1._
    val memento           = Film("Memento", 2000, 8.5)
    val darkKnight        = Film("Dark Knight", 2008, 9.0)
    val inception         = Film("Inception", 2010, 8.8)

    val highPlainsDrifter = Film("High Plains Drifter", 1973, 7.7)
    val outlawJoseyWales  = Film("The Outlaw Josey Wales", 1976, 7.9)
    val unforgiven        = Film("Unforgiven", 1992, 8.3)
    val granTorino        = Film("Gran Torino", 2008, 8.2)
    val invictus          = Film("Invictus", 2009, 7.4)

    val predator          = Film("Predator", 1987, 7.9)
    val dieHard           = Film("Die Hard", 1988, 8.3)
    val huntForRedOctober = Film("The Hunt for Red October", 1990, 7.6)
    val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8)

    val eastwood = Director("Clint", "Eastwood", 1930,
      Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

    val mcTiernan = Director("John", "McTiernan", 1951,
      Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

    val nolan = Director("Christopher", "Nolan", 1970,
      Seq(memento, darkKnight, inception))

    val someGuy = Director("Just", "Some Guy", 1990,
      Seq())

    val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  "tests for 6.1.9.3" should "be accurate" in {
    def directorsWithBackCatalogSize(numberOfFilms: Int): Seq[Director] =
      directors.filter(_.films.lengthCompare(numberOfFilms) >= 0)

    assert(directorsWithBackCatalogSize(3) == Seq(eastwood, mcTiernan, nolan))
    assert(directorsWithBackCatalogSize(4) == Seq(eastwood, mcTiernan))

    def bornBefore(year: Int): Option[Director] =
      directors.find(_.yearOfBirth < year)

    assert(bornBefore(1950).get == eastwood)
    assert(bornBefore(1900).isEmpty)

    def directorsBornBeforeWithBackCatalogSize(year: Int, numberOfFilms: Int): Seq[Director] = {
      val byAge   = directors.filter(_.yearOfBirth < year)
      val byFilms = directors.filter(_.films.lengthCompare(numberOfFilms) >= 0)
      byAge.filter(byFilms.contains)
    }

    assert(directorsBornBeforeWithBackCatalogSize(1950, 5) == Seq(eastwood))
    assert(directorsBornBeforeWithBackCatalogSize(1990, 2) ==
      Seq(eastwood, mcTiernan, nolan))
    assert(directorsBornBeforeWithBackCatalogSize(1990, 99) == Seq())
    assert(directorsBornBeforeWithBackCatalogSize(1890, 2) == Seq())

    def directorsSortedByAge(ascending: Boolean = true): Seq[Director] = {
      val comparator =
        if (ascending)
          (a: Director, b: Director) => a.yearOfBirth < b.yearOfBirth
        else
          (a: Director, b: Director) => a.yearOfBirth > b.yearOfBirth
      directors.sortWith(comparator)
    }

    assert(directorsSortedByAge() == Seq(eastwood, mcTiernan, nolan, someGuy))
    assert(directorsSortedByAge(false) == Seq(someGuy, nolan, mcTiernan, eastwood))

  }

  "Answers to 6.2.7" should "be accurate, and include details about type of the input data, result, and operation used" in {

    // Nolan Films
    // list containing the names of films directed by Christopher Nolan
    // Type of data available: Director, Seq[Film]
    // Type of result we want: Seq[String]
    //  Type of operation we will use: Map Film => String
    assert(nolan.films.map(_.name) == Seq("Memento", "Dark Knight", "Inception"))

    // Cinephile
    // List containing the names of all films by all directors
    // Type of data available Seq[Director]
    // Type of result we want: Seq[String]
    // Type of operations we will use:
    // FlatMap directors.Director.Films => Seq[Films]
    // Map Film => String
    assert(directors.flatMap(_.films).map(_.name) == Seq("High Plains Drifter", "The Outlaw Josey Wales", "Unforgiven", "Gran Torino", "Invictus", "Predator", "Die Hard", "The Hunt for Red October", "The Thomas Crown Affair", "Memento", "Dark Knight", "Inception"))

    // Vintage McTiernan
    // Date of the earlierst McTiernan film.
    // Type of data available: Director
    // Type of result we want: Int
    // Type of operation we will use: Fold (left or right both work)
    assert(mcTiernan.films.foldLeft(Int.MaxValue)((current, film) => math.min(current, film.yearOfRelease)) == predator.yearOfRelease)


    // High Score Table
    // Films sorted by descending IMBD rating
    // Available: List[Director]
    // Desired: Sorted[Film]
    // Operation to use: Director => Sorted[Film], List[Sorted[Film] => Sorted[Film]
    // Flatmap, sortWith
    directors.flatMap(_.films).sortWith(_.imdbRating > _.imdbRating)

    // Average score of all films by IMDB rating
    // Available: List[Director]
    // Desired: Int
    // Operations to use:
    val films = directors.flatMap(_.films)
    films.foldLeft(0.0)(_ + _.imdbRating) / films.size

    // Tonight's Listings
    // Print text for each film
    // Available: List[Director[Film]]
    // Operations to use: foreach (unit return type)
    directors.foreach((director) =>
      director.films.foreach((film) =>
        println(s"Tonight! ${film.name} by ${director.firstName} ${director.lastName}")))

    // Earliest film by any director
    // Available: List[Director]
    // Desired: Film
    // Operations to use: flatMap, sortWith
    directors.flatMap(_.films).sortWith(_.yearOfRelease < _.yearOfRelease).headOption
  }

  "Answers to 6.2.7.2" should "be correct" in {
    // Smalled element of a Seq[Int]
    def minimum(seq: Seq[Int]): Int =
      seq.foldLeft(Int.MaxValue)(math.min)
  }

  // Given Seq(1, 1, 2, 4, 3, 4), return a Seq with all elements only once,
  // order does not matter.
  // Use contains
  assert(Seq(1, 1, 2, 4, 3, 4).foldLeft(Seq(): Seq[Int])((acc, v) =>
    if (acc.contains(v)) acc else acc :+ v) == Seq(1, 2, 4, 3))

  // Reverse
  assert(Seq(1, 1, 2, 4, 3, 4).foldLeft(Seq(): Seq[Int])((acc, v) => v +: acc)
    == Seq(4, 3, 4, 2, 1, 1))

  // Map as foldRight
  def mapAsFoldRight[A, B](seq: Seq[A], f: A => B): Seq[B] =
    seq.foldRight(Seq.empty[B]){(elt, seq) => f(elt) +: seq}


  // foldLeft custom with mutation
  def foldLeftViaMutation[A, B](seq: Seq[A])(zero: B, f: (B, A) => B): B = {
    var result = zero
    seq.foreach { elt => result = f(result, elt) }
    result
   }

  "6.3.1 Exercises" should "be accurate" in {

  }
}

