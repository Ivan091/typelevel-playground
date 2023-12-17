package com.meineliebe.lox.typelevel.hlist

import com.meineliebe.lox.typelevel.Nat

trait HList
class HNil extends HList
class ::[H <: Nat, T <: HList] extends HList
