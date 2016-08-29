package com.example.sink

final case class StagedSink[First,Current,Final](staged: First => Traversable[Current], sink: Sink[Final]) {
  def map[B, To](f: Current => B)(implicit isDone: CanSink[First, B, Final, To]): To = isDone.result(this, f)
  def flatMap[B, To](f: Current => TraversableOnce[B])(implicit isDone: CanSink[First, B, Final, To]): To = isDone.result2(this, f)
  def withFilter(f: Current => Boolean): StagedSink[First, Current,Final] =
    StagedSink(staged andThen { xs => 
       for(x <- xs; if f(x)) yield x
    }, sink)
}
object StagedSink {
  def stagedIdentity[E]: E => Traversable[E] = (e: E) => List(e)
}
