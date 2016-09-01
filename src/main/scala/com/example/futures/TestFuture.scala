package com.example.futures

import emm._

import emm.compat.cats._
import cats.std.list._
import cats.std.option._
import cats.std.future._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object TestFuture {
  implicit val ec = scala.concurrent.ExecutionContext.global
  type E = Future |: Option |: Base

  val effect = Option(42).liftM[E]

  val effect2 = for {
    x <- effect
    y <- Future(None).liftM[E]
  } yield x

  def run = Await.result(effect2.run, 1 seconds)
}
