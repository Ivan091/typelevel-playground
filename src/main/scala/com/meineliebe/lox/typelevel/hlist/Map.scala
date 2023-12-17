package com.meineliebe.lox.typelevel.hlist

import com.meineliebe.lox.typelevel.Nat
import com.meineliebe.lox.typelevel.hlist.HNil

type G = Nat => Nat

trait HMap[F <: G, HL <: HList] {
  type R <: HList
}
type _HMap[F <: G, HL <: HList, O <: HList] = HMap[F, HL] {
  type R = O
}

object HMap {
  given basic[F <: G, HNil]: _HMap[F, HNil, HNil] = new HMap[F, HNil] {type R = HNil}
}
