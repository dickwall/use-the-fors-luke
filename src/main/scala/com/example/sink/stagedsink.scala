package com.example.sink

trait StagedSink[First, Current, Final] {
  def map[B, To](f: Current => B)(implicit isDone: CanSink[First, B, Final, To]): To
  def flatMap[B, To](f: Current => TraversableOnce[B])(implicit isDone: CanSink[First, B, Final, To]): To
  def withFilter(f: Current => Boolean): StagedSink[First, Current,Final]
}
