package com.meineliebe.typelevel.hlist

import com.meineliebe.typelevel.{hlist, Nat}

trait Append[HL <: HList, A <: Nat] {
  type R <: HList
}
type _Append[HL <: HList, A <: Nat, O <: HList] = Append[HL, A] {
  type R = O
}
object Append {
  given inductive[A <: Nat, HT <: HList](using concat: Concat[HT, A :: HNil]): _Append[HT, A, concat.Result] =
    new Append[HT, A] {
      type R = concat.Result
    }

  def test[HL <: HList, A <: Nat, O <: HList](using append: _Append[HL, A, O]): _Append[HL, A, O] = append
  def apply[HL <: HList, A <: Nat](using append: Append[HL, A]): _Append[HL, A, append.R] = append
}
