package com.meineliebe.lox.typelevel.hlist

import com.meineliebe.lox.typelevel.*

trait QSort[HL <: HList] {
  type Result <: HList
}
object QSort {
  private type _QSort[HL <: HList, O <: HList] = QSort[HL] { type Result = O }
  given basic: _QSort[HNil, HNil] = new QSort[HNil] { type Result = HNil }
  given inductive[N <: Nat, T <: HList, L <: HList, R <: HList, SL <: HList, SR <: HList](using
    _Partition[N :: T, N :: L, R],
    _QSort[L, SL],
    _QSort[R, SR]
  )(using
    concat: Concat[SL, N :: SR]
  ): _QSort[N :: T, concat.Result] = new QSort[N :: T] { type Result = concat.Result }
  def apply[L <: HList](using sort: QSort[L]): _QSort[L, sort.Result] = sort
}

trait Partition[HA <: HList] {
  type ResultLeft <: HList
  type ResultRight <: HList
}
type _Partition[A <: HList, HL <: HList, HR <: HList] = Partition[A] { type ResultLeft = HL; type ResultRight = HR }
object Partition {
  given basic: _Partition[HNil, HNil, HNil] = new Partition[HNil] { type ResultLeft = HNil; type ResultRight = HNil }
  given basic2[N <: Nat]: _Partition[N :: HNil, N :: HNil, HNil] = new Partition[N :: HNil] {
    type ResultLeft = N :: HNil
    type ResultRight = HNil
  }
  given inductive[P <: Nat, N <: Nat, T <: HList, L <: HList, R <: HList](using
    P <= N
  )(using part: Partition[P :: T]): _Partition[P :: N :: T, P :: part.ResultLeft, N :: part.ResultRight] = new Partition[P :: N :: T] {
    override type ResultLeft = P :: part.ResultLeft
    override type ResultRight = N :: part.ResultRight
  }

  given inductive2[P <: Nat, N <: Nat, T <: HList, L <: HList, R <: HList](using
    N < P)(using part:
  Partition[P :: T]
  ): _Partition[P :: N :: T, P :: N :: L, R] = new Partition[P :: N :: T] {
    override type ResultLeft = P :: N :: L
    override type ResultRight = R
  }

  def test[A <: HList, HL <: HList, HR <: HList](using partition: _Partition[A, HL, HR]): _Partition[A, HL, HR] = partition
  def apply[HL <: HList](using partition: Partition[HL]): _Partition[HL, partition.ResultLeft, partition.ResultRight] = partition
}
