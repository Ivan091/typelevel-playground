package com.meineliebe.lox.typelevel.math

import com.meineliebe.lox.typelevel.{_0, _1, Nat, Succ}

trait Sum[A <: Nat, B <: Nat] {
  type R <: Nat
}
type _Sum[A <: Nat, B <: Nat, O <: Nat] = Sum[A, B] { type R = O }
object Sum {
  given base00[A <: Nat]: _Sum[_0, _0, _0] = new Sum[_0, _0] { type R = _0 }
  given base10[A <: Nat]: _Sum[A, _0, A] = new Sum[A, _0] { type R = A }
  given base01[A <: Nat]: _Sum[_0, A, A] = new Sum[_0, A] { type R = A }

  given inductive[A <: Nat, B <: Nat](using s: Sum[A, B]): _Sum[Succ[A], Succ[B], Succ[Succ[s.R]]] = new Sum[Succ[A], Succ[B]] {
    type R = Succ[Succ[s.R]]
  }
  def test[A <: Nat, B <: Nat, O <: Nat](using s: _Sum[A, B, O]): _Sum[A, B, O] = s
  def apply[A <: Nat, B <: Nat](using s: Sum[A, B]): _Sum[A, B, s.R] = s
}
