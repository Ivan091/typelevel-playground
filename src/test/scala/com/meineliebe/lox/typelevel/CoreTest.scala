package com.meineliebe.lox.typelevel

import com.meineliebe.lox.typelevel.{< as lt, <= as ltq, == as teq}
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec

import scala.runtime.stdLibPatches.Predef.summon

class CoreTest extends AnyWordSpec with should.Matchers {

  "Dec" in {
    {
      val x = summon[Dec[_9]]
      teq[x.Result, _8]
    }
    {
      val x = summon[Dec[_1]]
      teq[x.Result, _0]
    }
  }

  "==" in {
    teq[_0, _0]
    teq[_3, _3]
  }

  "<" in {
    lt[_0, _1]
    lt[_7, _9]
  }

  "<=" in {
    ltq[_0, _0]
    ltq[_1, _9]
    ltq[_9, _9]
  }
}
