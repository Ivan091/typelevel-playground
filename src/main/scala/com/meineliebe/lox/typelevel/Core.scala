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
type _Dec[N <: Nat, O <: Nat] = Dec[N] { type R = O }
object Dec {
  given basic: _Dec[Succ[_0], _0] = new Dec[Succ[_0]] { type R = _0 }
  given inductive[N <: Nat](using d: Dec[N]): _Dec[Succ[N], Succ[d.R]] = new Dec[Succ[N]] { type R = Succ[d.R] }
  def test[N <: Nat, O <: Nat](using dec: _Dec[N, O]): _Dec[N, O] = dec
  def apply[N <: Nat](using dec: Dec[N]): _Dec[N, dec.R] = dec
}

//trait Apply[F[_ <: Nat, _ <: Nat], A <: Nat] {
//  type R <: Nat
//}
//
//type _Apply[F[_ <: Nat, _ <: Nat], A <: Nat, O <: Nat] = Apply[F, A] {type R = O}
//
//object Apply {
//  def test[F[_ <: Nat, _ <: Nat], A <: Nat, O <: Nat](using f: F[A, O]): _Apply[F, A, O] = new Apply[F, A] {
//    type R = O
//  }
//}

trait Apply[F[_ <: Nat, _ <: Nat], A <: Nat] {
  type R <: Nat
}

type _Apply[F[_ <: Nat, _ <: Nat], A <: Nat, O <: Nat] = Apply[F, A] { type R = O }

object Apply {
  given basic[F[_ <: Nat, _ <: Nat], A <: Nat, O <: Nat](using F[A, O]): _Apply[F, A, O] = new Apply[F, A] {
    type R = O
  }

  def test[F[_ <: Nat, _ <: Nat], A <: Nat, O <: Nat](using ap: Apply[F, A]): _Apply[F, A, ap.R] = new Apply[F, A] {
    type R = ap.R
  }

  def apply[F[_ <: Nat, _ <: Nat], A <: Nat](using ap: Apply[F, A]): _Apply[F, A, ap.R] = ap
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
