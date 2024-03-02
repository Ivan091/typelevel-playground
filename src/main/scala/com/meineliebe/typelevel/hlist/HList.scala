package com.meineliebe.typelevel.hlist

import com.meineliebe.typelevel.Nat

trait HList
class HNil extends HList
class ::[H <: Nat, T <: HList] extends HList
