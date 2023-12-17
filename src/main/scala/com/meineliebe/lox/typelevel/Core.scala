package com.meineliebe.lox.typelevel

import scala.annotation.targetName

trait Nat
class _0 extends Nat
class Succ[N <: Nat] extends Nat
type _1 = Succ[_0]
type _2 = Succ[_1]
type _3 = Succ[_2]
type _4 = Succ[_3]
type _5 = Succ[_4]
type _6 = Succ[_5]
type _7 = Succ[_6]
type _8 = Succ[_7]
type _9 = Succ[_8]

trait Dec[N <: Nat] {
  type R <: Nat
}

object Dec {
  private type _Dec[N <: Nat, O <: Nat] = Dec[N] { type R = O }
  given basic: _Dec[Succ[_0], _0] = new Dec[Succ[_0]] { type R = _0 }
  given inductive[N <: Nat](using d: Dec[N]): _Dec[Succ[N], Succ[d.R]] = new Dec[Succ[N]] { type R = Succ[d.R] }
  def test[N <: Nat, O <: Nat](using dec: _Dec[N, O]): _Dec[N, O] = dec
  def apply[N <: Nat](using dec: Dec[N]): Dec[N] = dec
}

trait ==[A <: Nat, B <: Nat]
object == {
  given basic: ==[_0, _0]()
  given inductive[A <: Nat, B <: Nat](using eq: ==[A, B]): ==[Succ[A], Succ[B]]()
  def apply[A <: Nat, B <: Nat](using eq: ==[A, B]): ==[A, B] = eq
}

trait <[A <: Nat, B <: Nat]
object < {
  given basic[B <: Nat]: <[_0, Succ[B]]()
  given inductive[A <: Nat, B <: Nat](using lt: <[A, B]): <[Succ[A], Succ[B]]()
  def apply[A <: Nat, B <: Nat](using lt: <[A, B]): <[A, B] = lt
}

trait <=[A <: Nat, B <: Nat]
object <= {
  given basic[B <: Nat]: <=[_0, B]()
  given inductive[A <: Nat, B <: Nat](using ltq: <=[A, B]): <=[Succ[A], Succ[B]]()
  def apply[A <: Nat, B <: Nat](using ltq: <=[A, B]): <=[A, B] = ltq
}
