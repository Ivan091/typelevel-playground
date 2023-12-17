package com.meineliebe.lox.typelevel.hlist

import com.meineliebe.lox.typelevel.*


trait ===[HA <: HList, HB <: HList]
object === {
  given basicEmpty: ===[HNil, HNil]()
  given inductive[A <: Nat, HA <: HList, B <: Nat, HB <: HList](using A == B, HA === HB): ===[A :: HA, B :: HB]()
  def apply[HA <: HList, HB <: HList](using eq: ===[HA, HB]): ===[HA, HB] = eq
}
