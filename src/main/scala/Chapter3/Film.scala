package Chapter3

case class Film(name: String,
           yearOfRelease: Int,
           imdbRating: Double,
           director: Director) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director): Boolean = this.director == director
}

object Film {
  def highestRated(first: Film, second: Film): Film =
    if (first.imdbRating >= second.imdbRating) first else second

  def oldestDirectorAtTheTime(first: Film, second: Film): Director =
    if (first.directorsAge >= second.directorsAge) first.director
    else second.director
}
