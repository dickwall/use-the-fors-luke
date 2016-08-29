package com.example.sink

trait CanSink[First, Now, Final, To] {
  def result[E](in: StagedSink[First, E, Final], f: E => Now): To
  def result2[E](in: StagedSink[First, E, Final], f: E => TraversableOnce[Now]): To
}
trait LowPrioritySinkImplicits {
  implicit def sinkChain[First, E, Final]: CanSink[First, E, Final, StagedSink[First, E, Final]] = ???
}
object CanSink extends LowPrioritySinkImplicits {
  implicit def finalSink[First, E]: CanSink[First, E,E, Sink[First]] = ???
}
