package com.meineliebe.lox.typelevel.hlist

import com.meineliebe.lox.typelevel.*

trait BSort[HL <: HList, Cnt <: Nat] {
  type R <: HList
}
type _BSort[HL <: HList, Cnt <: Nat, O <: HList] = BSort[HL, Cnt] { type R = O }
object BSort {
  given basic1[T <: HList]: _BSort[T, _0, T] = new BSort[T, _0] {
    type R = T
  }
  given inductive1[Cnt <: Nat](using _BSort[HNil, Cnt, HNil]): _BSort[HNil, Succ[Cnt], HNil] = new BSort[HNil, Succ[Cnt]] {
    type R = HNil
  }
  given basic2[N <: Nat]: _BSort[N :: HNil, _0, N :: HNil] = new BSort[N :: HNil, _0] {
    type R = N :: HNil
  }

  given inductive[Cnt <: Nat, T <: HList](using switch: Switch[T], dec: Dec[Cnt])(using
    bsort: BSort[switch.R, dec.R]
  ): _BSort[T, Cnt, bsort.R] = new BSort[T, Cnt] {
    type R = bsort.R
  }
  def test[HL <: HList, O <: HList](using count: Count[HL])(using sort: _BSort[HL, count.Result, O]): _BSort[HL, count.Result, O] = sort
  def apply[HL <: HList](using count: Count[HL])(using sort: BSort[HL, count.Result]): _BSort[HL, count.Result, sort.R] = sort
}

trait Switch[HL <: HList] {
  type R <: HList
}
type _Switch[HL <: HList, O <: HList] = Switch[HL] { type R = O }
object Switch {
  given basic: _Switch[HNil, HNil] = new Switch[HNil] { type R = HNil }
  given basic2[N <: Nat]: _Switch[N :: HNil, N :: HNil] = new Switch[N :: HNil] {
    type R = N :: HNil
  }
  given inductive[N <: Nat, P <: Nat, Q <: Nat, T <: HList, Ri <: HList](using
    N <= P,
    _Switch[P :: T, Q :: Ri]
  ): _Switch[N :: P :: T, N :: Q :: Ri] = new Switch[N :: P :: T] {
    type R = N :: Q :: Ri
  }

  given inductive2[N <: Nat, P <: Nat, Q <: Nat, T <: HList, Ri <: HList](using
    P < N,
    _Switch[N :: T, Q :: Ri]
  ): _Switch[N :: P :: T, P :: Q :: Ri] = new Switch[N :: P :: T] {
    type R = P :: Q :: Ri
  }

  def test[HL <: HList, O <: HList](using switch: _Switch[HL, O]): _Switch[HL, O] = switch
  def apply[HL <: HList](using switch: Switch[HL]): _Switch[HL, switch.R] = switch
}
