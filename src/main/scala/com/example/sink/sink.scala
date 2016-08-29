package com.example.sink

trait Sink[To] {
  def apply(t: To): Unit
  def stage[E]: StagedSink[E, E, To] = ???
}
