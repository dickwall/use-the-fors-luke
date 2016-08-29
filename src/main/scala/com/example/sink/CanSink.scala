package com.example.sink

trait CanSink[First, Now, Final, To] {
  def result[E](in: StagedSink[First, E, Final], f: E => Now): To
  def result2[E](in: StagedSink[First, E, Final], f: E => TraversableOnce[Now]): To
}
trait LowPrioritySinkImplicits {
  implicit def sinkChain[First, Now, Final]: CanSink[First, Now, Final, StagedSink[First, Now, Final]] =
    new CanSink[First,Now, Final, StagedSink[First, Now, Final]] {
      def result[E](in: StagedSink[First, E, Final], f: E => Now): StagedSink[First, Now, Final] =
        StagedSink(in.staged andThen (x => x map f), in.sink)
      def result2[E](in: StagedSink[First, E, Final], f: E => TraversableOnce[Now]): StagedSink[First, Now, Final] =
        StagedSink(in.staged andThen (x => x flatMap f), in.sink)
    }
}
object CanSink extends LowPrioritySinkImplicits {
  implicit def finalSink[First, E]: CanSink[First, E,E, Sink[First]] =
    new CanSink[First, E,E, Sink[First]] {
      def result[A](ss: StagedSink[First, A, E], f: A => E): Sink[First] =
        new Sink[First] {
          def apply(in: First): Unit = {
            val staged = ss.staged.andThen { xs => xs map f }
            for { x <- staged(in) } ss.sink(x)
          }
        }
      def result2[A](ss: StagedSink[First, A, E], f: A => TraversableOnce[E]): Sink[First] =
        new Sink[First] {
          def apply(in: First): Unit = {
            val staged = ss.staged.andThen { xs => xs flatMap f }
            for { x <- staged(in) } ss.sink(x)
          }
        }
    }

}
