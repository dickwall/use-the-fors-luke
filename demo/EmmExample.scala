import ammonite._, Resolvers._

val emmResolver = Resolver.Http(
  "bintray-djspiewak-maven",
  "https://dl.bintray.com/djspiewak/maven",
  IvyPattern,
  false
)

interp.resolvers() = interp.resolvers() :+ emmResolver

interp.load.ivy("com.codecommit" %% "emm-cats" % "0.2.1")

import emm._

import emm.compat.cats._
import cats.std.list._
import cats.std.option._
import cats.std.future._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

implicit val ec = scala.concurrent.ExecutionContext.global
type E = Future |: Option |: Base

val optionA = Option(3)

val effect = optionA.liftM[E]

val optionB: Option[Int] = None

val effect2 = Option.empty[Int].liftM[E]

val effect3 = Future(7).liftM[E]

val effect4 = for {
  x <- effect
  y <- effect2
  z <- effect3
} yield x * y * z

Await.result(effect4.run, 1 seconds)


effect.run
effect2.run
effect3.run


val optionA = Some(3)
val optionB = Some(2)


val res2 = for {
  x <- Future(7)
  res = for (y <- optionA; z <- optionB) yield x * y * z
} yield res

Await.result(res2, 1.second)

val res3 = for {
  x <- Futnew ${clazz.name}ure(7)
} yield {
  for {
    i <- optionA
    j <- optionB
  } yield i * j * x
}

Await.result(res3, 1.second)

def multOpt3(x: Option[Int], y: Option[Int], z: Option[Int]): Option[Int] =
  for {
    a <- x
    b <- y
    c <- z
  } yield a * b * c


val res4 = for {
  x <- Future(7)
  ox = Option(x)
} yield multOpt3(ox, optionA, optionB)

Await.result(res4, 1.second)

def sequence[A](x: Option[Future[A]]): Future[Option[A]] = {
  x match {
    case None => Future(None)
    case Some(f) => f.map(Option.apply)
  }
}

val ex1 = Some(Future(42))

val res5 = sequence(ex1)

Await.result(res5, 1.second)
