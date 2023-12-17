package com.meineliebe.lox.typelevel

import com.meineliebe.lox.typelevel.{< as lt, <= as ltq, == as teq, === as heq}
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec

class HListTest extends AnyWordSpec with should.Matchers {

  "HListEq" in {
    heq[_0 :: _1 :: HNil, _0 :: _1 :: HNil]
    heq[_1 :: _2 :: _3 :: _0 :: HNil, _1 :: _2 :: _3 :: _0 :: HNil]
  }
}
