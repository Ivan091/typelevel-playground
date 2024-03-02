package com.meineliebe.typelevel.hlist

import com.meineliebe.typelevel.*
import com.meineliebe.typelevel.hlist.*

trait Concat[HA <: HList, HB <: HList] {
  type Result <: HList
}
type _Concat[HA <: HList, HB <: HList, O <: HList] = Concat[HA, HB] { type Result = O }
object Concat {
  given basicEmpty[HL <: HList]: _Concat[HNil, HL, HL] = new Concat[HNil, HL] { type Result = HL }
  given inductive[N <: Nat, HA <: HList, HB <: HList](using concat: Concat[HA, HB]): _Concat[N :: HA, HB, N :: concat.Result] =
    new Concat[N :: HA, HB] {
      type Result = N :: concat.Result
    }
  def test[HA <: HList, HB <: HList, O <: HList](using concat: _Concat[HA, HB, O]): _Concat[HA, HB, O] = concat
  def apply[HA <: HList, HB <: HList](using concat: Concat[HA, HB]): Concat[HA, HB] = concat
}
