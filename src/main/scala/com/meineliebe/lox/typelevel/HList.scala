package com.meineliebe.lox.typelevel

import scala.collection.IndexedSeqView.Concat

trait HList
class HNil extends HList
class ::[H <: Nat, T <: HList] extends HList

trait ===[HA <: HList, HB <: HList]
object === {
  given basicEmpty: ===[HNil, HNil]()
  given inductive[A <: Nat, HA <: HList, B <: Nat, HB <: HList](using A == B): ===[HA, HB]()
  def apply[HA <: HList, HB <: HList](using eq: ===[HA, HB]): ===[HA, HB] = eq
}

trait Concat[HA <: HList, HB <: HList] {
  type Result <: HList
}
object Concat {
  private type _Concat[HA <: HList, HB <: HList, O <: HList] = Concat[HA, HB] { type Result = O }
  given basicEmpty[HL <: HList]: _Concat[HNil, HL, HL] = new Concat[HNil, HL] { type Result = HL }
  given inductive[N <: Nat, HA <: HList, HB <: HList](using concat: Concat[HA, HB]): _Concat[N :: HA, HB, N :: concat.Result] =
    new Concat[N :: HA, HB] {
      type Result = N :: concat.Result
    }
  def apply[HA <: HList, HB <: HList](using concat: Concat[HA, HB]): Concat[HA, HB] = concat
}

trait Count[HL <: HList] {
  type Result <: Nat
}
object Count {
  private type _Count[HL <: HList, O <: Nat] = Count[HL] { type Result = O }
  given basic: _Count[HNil, _0] = new Count[HNil] { type Result = _0 }
  given inductive[N <: Nat, T <: HList](using
    count: Count[T]
  ): _Count[N :: T, Succ[count.Result]] = new Count[N :: T] { type Result = Succ[count.Result] }
  def apply[HL <: HList](using count: Count[HL]): Count[HL] = count
}

trait Partition[HA <: HList, HB <: HList, O <: HList]
object Partition {
  given basic: Partition[HNil, HNil, HNil]()
  given basic2[N <: Nat]: Partition[N :: HNil, N :: HNil, HNil]()
  given inductive[P <: Nat, N <: Nat, T <: HList, L <: HList, R <: HList](using
    P <= N,
    Partition[P :: T, P :: L, R]
  ): Partition[P :: N :: T, P :: L, N :: R]()

  given inductive2[P <: Nat, N <: Nat, T <: HList, L <: HList, R <: HList](using
    N < P,
    Partition[P :: T, P :: L, R]
  ): Partition[P :: N :: T, P :: N :: L, R]()

  def apply[HL <: HList, L <: HList, R <: HList](using partition: Partition[HL, L, R]): Partition[HL, L, R] = partition
}

trait QSort[HL <: HList] {
  type Result <: HList
}
object QSort {
  private type _QSort[HL <: HList, O <: HList] = QSort[HL] { type Result = O }
  given basic: _QSort[HNil, HNil] = new QSort[HNil] { type Result = HNil }
  given inductive[N <: Nat, T <: HList, L <: HList, R <: HList, SL <: HList, SR <: HList](using
    Partition[N :: T, N :: L, R],
    _QSort[L, SL],
    _QSort[R, SR]
  )(using
    concat: Concat[SL, N :: SR]
  ): _QSort[N :: T, concat.Result] = new QSort[N :: T] { type Result = concat.Result }
  def apply[L <: HList](using sort: QSort[L]): _QSort[L, sort.Result] = sort
}

trait BSort[HL <: HList, Cnt <: Nat] {
  type Result <: HList
}
object BSort {
  type _BSort[HL <: HList, Cnt <: Nat, O <: HList] = BSort[HL, Cnt] { type Result = O }

  given basic1[T <: HList]: _BSort[T, _0, T] = new BSort[T, _0] {
    type Result = T
  }

  given inductive1[Cnt <: Nat](using _BSort[HNil, Cnt, HNil]): _BSort[HNil, Succ[Cnt], HNil] = new BSort[HNil, Succ[Cnt]] {
    type Result = HNil
  }

  given basic2[N <: Nat]: _BSort[N :: HNil, _0, N :: HNil] = new BSort[N :: HNil, _0] {
    type Result = N :: HNil
  }

  given inductive2[N <: Nat, Cnt <: Nat](using _BSort[N :: HNil, Cnt, N :: HNil]): _BSort[N :: HNil, Succ[Cnt], N :: HNil] =
    new BSort[N :: HNil, Succ[Cnt]] {
      type Result = N :: HNil
    }

  given inductive[Cnt <: Nat, T <: HList, TR <: HList, R <: HList](using Switch[T, TR])(using dec: Dec[Cnt])(using
    _BSort[TR, dec.Result, R]
  ): _BSort[T, Cnt, R] = new BSort[T, Cnt] {
    type Result = R
  }

  def apply[HL <: HList](using count: Count[HL])(using sort: BSort[HL, count.Result]): _BSort[HL, count.Result, sort.Result] = sort
}

trait Switch[HL <: HList, O <: HList]
object Switch {
  given basic: Switch[HNil, HNil]()
  given basic2[N <: Nat]: Switch[N :: HNil, N :: HNil]()
  given inductive[N <: Nat, P <: Nat, Q <: Nat, T <: HList, R <: HList](using
    N <= P,
    Switch[P :: T, Q :: R]
  ): Switch[N :: P :: T, N :: Q :: R]()

  given inductive2[N <: Nat, P <: Nat, Q <: Nat, T <: HList, R <: HList](using
    P < N,
    Switch[N :: T, Q :: R]
  ): Switch[N :: P :: T, P :: Q :: R]()

  def apply[HL <: HList, O <: HList](using switch: Switch[HL, O]): Switch[HL, O] = switch
}
