object ForExpansion1 {
  val mults = for {
    i <- 1 to 10
    j <- 1 to 10
    if i % 3 == 0 || j % 3 == 0
    k <- 1 to 10
    if i * j * k % 2 == 0
  } yield i * j * k
}
