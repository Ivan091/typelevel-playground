package com.meineliebe.typelevel.math

import com.meineliebe.typelevel.*

trait Multiply[A <: Nat, B <: Nat] {
  type R <: Nat
}
type _Multiply[A <: Nat, B <: Nat, O <: Nat] = Multiply[A, B] { type R = O }

object Multiply {
  given base[A <: Nat]: _Multiply[A, _0, _0] = new Multiply[A, _0] { type R = _0 }
  given inductive[A <: Nat, B <: Nat](using m: Multiply[A, B])(using s: Sum[m.R, A]): _Multiply[A, Succ[B], s.R] =
    new Multiply[A, Succ[B]] {
      type R = s.R
    }

  def test[A <: Nat, B <: Nat, O <: Nat](using s: _Multiply[A, B, O]): _Multiply[A, B, O] = s
  def apply[A <: Nat, B <: Nat](using s: Multiply[A, B]): _Multiply[A, B, s.R] = s
}
