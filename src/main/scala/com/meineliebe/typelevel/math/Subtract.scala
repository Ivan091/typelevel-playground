package com.meineliebe.typelevel.math

import com.meineliebe.typelevel.*

trait Subtract[A <: Nat, B <: Nat] {
  type R <: Nat
}
type _Subtract[A <: Nat, B <: Nat, O <: Nat] = Subtract[A, B] { type R = O }

object Subtract {
  given base[A <: Nat]: _Subtract[A, _0, A] = new Subtract[A, _0] { type R = A }
  given inductive[A <: Nat, B <: Nat](using s: Subtract[A, B]): _Subtract[Succ[A], Succ[B], s.R] = new Subtract[Succ[A], Succ[B]] {
    type R = s.R
  }

  def test[A <: Nat, B <: Nat, O <: Nat](using s: _Subtract[A, B, O]): _Subtract[A, B, O] = s
  def apply[A <: Nat, B <: Nat](using s: Subtract[A, B]): _Subtract[A, B, s.R] = s
}
