import Chapter3._
import org.scalatest._

class Chapter3Spec extends FlatSpec {
  "ChipShop" should "Accept a Chapter3.Cat and return true if the cat's favourite food is chips, and false otherwise." in {
    val eatsChips = new Cat("Ginger", "Chips")
    val notChips = new Cat("Tabby and white", "Curry")
    assert(ChipShop willServe eatsChips)
    assert(!ChipShop.willServe(notChips))
  }

  "Chapter3.Director" should "include a name method that returns the full name" in {
    val director = new Director("Jim", "Bob", 1991)
    assert(director.firstName == "Jim")
    assert(director.lastName == "Bob")
    assert(director.name == "Jim Bob")
  }

  "Chapter3.Film" should "A method directorsAge that returns the age of the director at the time of release.\n" +
    "A method isDirectedBy that accepts a Chapter3.Director as a parameter and returns a Boolean" in {
    val director = new Director("Jim", "Bob", 1991)
    val falseDirector = new Director("Joe", "Bob", 1991)
    val film = new Film("Name", 2001, 8.6, director)
    assert(film.directorsAge == 10)
    assert(film isDirectedBy director)
    assert(!(film isDirectedBy falseDirector))
  }

  "Chapter3.Film and Chapter3.Director" should "Work with the following test data from essential scala" in {
    val eastwood          = new Director("Clint", "Eastwood", 1930)
    val mcTiernan         = new Director("John", "McTiernan", 1951)
    val nolan             = new Director("Christopher", "Nolan", 1970)
    val someBody          = new Director("Just", "Some Body", 1990)

    val memento           = new Film("Memento", 2000, 8.5, nolan)
    val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception         = new Film("Inception", 2010, 8.8, nolan)

    val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7, eastwood)
    val outlawJoseyWales  = new Film("The Outlaw Josey Wales", 1976, 7.9, eastwood)
    val unforgiven        = new Film("Unforgiven", 1992, 8.3, eastwood)
    val granTorino        = new Film("Gran Torino", 2008, 8.2, eastwood)
    val invictus          = new Film("Invictus", 2009, 7.4, eastwood)

    val predator          = new Film("Predator", 1987, 7.9, mcTiernan)
    val dieHard           = new Film("Die Hard", 1988, 8.3, mcTiernan)
    val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
    val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)

    assert(eastwood.yearOfBirth == 1930)
    assert(dieHard.director.name == "John McTiernan")
    assert(!invictus.isDirectedBy(nolan))
    assert(
      thomasCrownAffair.copy(yearOfRelease = 1968,
                             director = new Director("Norman", "Jewison", 1926))
        == new Film("The Thomas Crown Affair", 1968, 6.8,
                    new Director("Norman", "Jewison", 1926)))
    assert(highPlainsDrifter.copy(name = "L'homme des hautes plaines")
      == new Film("L'homme des hautes plaines", 1973, 7.7, eastwood))
    assert(inception.copy().copy().copy() == inception)
  }

  "Chapter3.Counter" should "Allow incrementing and decrementing by arbitrary amounts" in {
    val count10: Counter = new Counter(10)
    assert(count10.inc.dec.inc.inc.count == 12)
    assert(count10.inc(5).count == 15)
    assert(count10.dec(3).count == 7)
    val add5: Adder = Adder(5)
    assert(count10.adjust(add5).count == 15)
  }

  "Chapter3.Timestamp" should "Use a singleton object constructor" in {
    assert(Timestamp(1, 1, 1).seconds == 3661)
  }

  "Chapter3.Person" should "Have a companion object with an apply method that accepts a whole name as a string. Should only take 1 parameter" in {
    val johnDoe = Person("John Doe")
    assert(johnDoe.firstName == "John")
    assert(johnDoe.lastName == "Doe")
  }

  "Chapter3.Director companion object" should "Have an apply constructor" in {
    val director: Director = Director("John", "Doe", 1991)
    val originalDirector: Director = new Director("John", "Doe", 1991)
    assert(director == originalDirector)
  }

  "Chapter3.Director companion object" should "Have a method older that picks the older of two directors" in {
    val older: Director = Director("John", "Doe Sr.", 1961)
    val younger: Director = Director("John", "Doe Jr.", 1991)
    assert(Director.older(younger, older) == older)
  }

  "Chapter3.Film companion object" should "Have an apply constructor" in {
    val nolan                   = new Director("Christopher", "Nolan", 1970)
    val darkKnight              = new Film("Dark Knight", 2008, 9.0, nolan)
    val darkKnightNoConstructor = Film("Dark Knight", 2008, 9.0, nolan)
    assert(darkKnightNoConstructor == darkKnight)
  }

  "Chapter3.Film companion object" should "Have a highestRating method that returns the higher rated of 2 films" in {
    val nolan                   = new Director("Christopher", "Nolan", 1970)
    val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception         = new Film("Inception", 2010, 8.8, nolan)
    assert(Film.highestRated(darkKnight, inception) == darkKnight)
  }

  "Chapter3.Film companion object" should "Have a method oldestDirectorAtTheTime that accepts two Films and returns the Director who was oldest at the respective time of filming." in {
    val nolan                   = new Director("Christopher", "Nolan", 1970)
    val mcTiernan         = new Director("John", "McTiernan", 1951)
    val darkKnight        = new Film("Dark Knight", 2008, 9.0, nolan)
    val inception         = new Film("Inception", 2010, 8.8, nolan)
    val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6, mcTiernan)
    val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8, mcTiernan)
    assert(Film.oldestDirectorAtTheTime(darkKnight, thomasCrownAffair) == mcTiernan)
    assert(Film.oldestDirectorAtTheTime(inception, huntForRedOctober) == nolan)
  }
}
