package Chapter3

class Film(val name: String,
           val yearOfRelease: Int,
           val imdbRating: Double,
           val director: Director) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director): Boolean = this.director == director
  def copy(name: String = this.name,
           yearOfRelease: Int = this.yearOfRelease,
           imdbRating: Double = this.imdbRating,
           director: Director = this.director): Film = {
    new Film(name, yearOfRelease, imdbRating, director)
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Film]

  override def equals(other: Any): Boolean = other match {
    case that: Film =>
      (that canEqual this) &&
        name == that.name &&
        yearOfRelease == that.yearOfRelease &&
        imdbRating == that.imdbRating &&
        director == that.director
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(name, yearOfRelease, imdbRating, director)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

object Film {
  def apply(name: String, yearOfRelease: Int,
            imdbRating: Double, director: Director): Film =
    new Film(name, yearOfRelease, imdbRating, director)

  def highestRated(first: Film, second: Film): Film =
    if (first.imdbRating >= second.imdbRating) first else second

  def oldestDirectorAtTheTime(first: Film, second: Film): Director =
    if (first.directorsAge >= second.directorsAge) first.director
    else second.director
}
