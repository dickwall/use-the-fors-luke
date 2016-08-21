object ForExpansion1 {
  val mults = for {
    i <- 1 to 10
    j <- 1 to 10
    if i % 3 == 0 || j % 3 == 0
    k <- 1 to 10
    mult = i * j * k
    if mult % 2 == 0
  } yield mult
}
