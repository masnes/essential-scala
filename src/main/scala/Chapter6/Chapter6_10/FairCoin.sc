import Chapter6.Chapter6_10._

sealed trait Coin
final case object Heads extends Coin
final case object Tails extends Coin

val fairCoin: Distribution[Coin] = Distribution.uniform(List(Heads, Tails))

val weightedCoin: Distribution[Coin] =
  Distribution(List(Heads -> .75, Tails -> .25))


val threeFlips =
  for {
    c1 <- fairCoin
    c2 <- fairCoin
    c3 <- fairCoin
 } yield (c1, c2, c3)

val threeFlipsWeighted =
  for {
    c1 <- weightedCoin
    c2 <- weightedCoin
    c3 <- weightedCoin
  } yield (c1, c2, c3)
  
