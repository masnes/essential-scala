package Chapter6.Chapter6_1


case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

