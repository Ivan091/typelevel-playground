package com.meineliebe.typelevel.math

import com.meineliebe.typelevel.*

trait Divide[A <: Nat, B <: Nat] {
  type R <: Nat
}

type _Divide[A <: Nat, B <: Nat, O <: Nat] = Divide[A, B] { type R = O }

object Divide {
  given base11[A <: Nat]: _Divide[A, A, _1] = new Divide[A, A] { type R = _1 }

  given inductive1[A <: Nat, B <: Nat](using B < A)(using s: Subtract[A, B])(using d: Divide[s.R, B]): _Divide[A, B, Succ[d.R]] =
    new Divide[A, B] { type R = Succ[d.R] }

  given inductive2[A <: Nat, B <: Nat](using A < B): _Divide[A, B, _0] =
    new Divide[A, B] {
      type R = _0
    }

  def test[A <: Nat, B <: Nat, O <: Nat](using s: _Divide[A, B, O]): _Divide[A, B, O] = s
  def apply[A <: Nat, B <: Nat](using s: Divide[A, B]): _Divide[A, B, s.R] = s
}
