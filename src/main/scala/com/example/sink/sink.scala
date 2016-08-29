package com.example.sink

trait Sink[To] { sink =>
  def apply(t: To): Unit
  final def stage[E]: StagedSink[E, E, To] = StagedSink(StagedSink.stagedIdentity[E], this)
}


