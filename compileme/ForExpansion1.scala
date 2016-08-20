object ForExpansion1 {
  val mults = for {
    i <- 1 to 3 // flatMap
    j <- 1 to 3 // flatMap
    k <- 1 to 3 // map
  } yield i*j*k
}
