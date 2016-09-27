for (i <- 1 to 5) println(i)

desugar {
  for (i <- 1 to 10) println(i)
}


for {
  i <- 1 to 3
  j <- 1 to 2
  k <- 1 to 2
} yield i * j * k


desugar {
  for {
    i <- 1 to 3
    j <- 1 to 2
    k <- 1 to 2
  } yield i * j * k
}

for {
  i <- 1 to 5
  if (i % 2 == 0)
  _ = println(i)
  j <- 1 to 4
} yield i * j


desugar {
  for {
    i <- 1 to 5
    if (i % 2 == 0)
    _ = println(i)
    j <- 1 to 4
  } yield i * j
}

for {
  i <- 1 to 10
  if i % 3 == 0
  j <- 1 to 20
  prod = i * j
  if prod % 5 == 0
} yield prod

desugar {
  for {
    i <- 1 to 10
    if i % 3 == 0
    j <- 1 to 20
    prod = i * j
    if prod % 5 == 0
  } yield prod
}
