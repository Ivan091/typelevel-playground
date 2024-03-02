package com.meineliebe.typelevel.hlist

import com.meineliebe.typelevel.*
import com.meineliebe.typelevel.hlist.*

trait Count[HL <: HList] {
  type Result <: Nat
}
object Count {
  private type _Count[HL <: HList, O <: Nat] = Count[HL] { type Result = O }
  given basic: _Count[HNil, _0] = new Count[HNil] { type Result = _0 }
  given inductive[N <: Nat, T <: HList](using count: Count[T]): _Count[N :: T, Succ[count.Result]] = new Count[N :: T] {
    type Result = Succ[count.Result]
  }
  def test[HL <: HList, O <: Nat](using count: _Count[HL, O]): _Count[HL, O] = count
  def apply[HL <: HList](using count: Count[HL]): Count[HL] = count
}
