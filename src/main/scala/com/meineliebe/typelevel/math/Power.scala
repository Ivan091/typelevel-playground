package com.meineliebe.typelevel.math

import com.meineliebe.typelevel.*

trait Power[A <: Nat, B <: Nat] {
  type R <: Nat
}
type _Power[A <: Nat, B <: Nat, O <: Nat] = Power[A, B] { type R = O }

object Power {
  given base[A <: Nat]: _Power[A, _0, _1] = new Power[A, _0] { type R = _1 }
  given inductive[A <: Nat, B <: Nat](using p: Power[A, B])(using m: Multiply[p.R, A]): _Power[A, Succ[B], m.R] =
    new Power[A, Succ[B]] {
      type R = m.R
    }

  def test[A <: Nat, B <: Nat, O <: Nat](using p: _Power[A, B, O]): _Power[A, B, O] = p
  def apply[A <: Nat, B <: Nat](using p: Power[A, B]): _Power[A, B, p.R] = p
}
