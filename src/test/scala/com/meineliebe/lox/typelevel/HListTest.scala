package com.meineliebe.lox.typelevel

import com.meineliebe.lox.typelevel.{< as lt, <= as ltq, == as teq, *}
import com.meineliebe.lox.typelevel.hlist.{=== as hleq, *}
import com.meineliebe.lox.typelevel.hlist.BSort.given
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import org.tpolecat.typename.TypeName

class HListTest extends AnyWordSpec with should.Matchers {

  "===" in {
    hleq[_0 :: _1 :: HNil, _0 :: _1 :: HNil]
    hleq[_1 :: _2 :: _3 :: _0 :: HNil, _1 :: _2 :: _3 :: _0 :: HNil]
  }

  "concat" in {
    Concat.test[HNil, HNil, HNil]
    Concat.test[_0 :: HNil, HNil, _0 :: HNil]
    Concat.test[HNil, _0 :: HNil, _0 :: HNil]
    Concat.test[_1 :: HNil, _0 :: HNil, _1 :: _0 :: HNil]
  }

  "count" in {
    Count.test[HNil, _0]
    Count.test[_1 :: HNil, _1]
    Count.test[_0 :: _1 :: _3 :: HNil, _3]
  }

  "append" in {
    Append.test[HNil, _0, _0 :: HNil]
    Append.test[_1 :: HNil, _0, _1 :: _0 :: HNil]
    Append.test[_1 :: _2 :: _3 :: _4 :: HNil, _0, _1 :: _2 :: _3 :: _4 :: _0 :: HNil]
  }


  "sort" when {
    "bubble" in {
      BSort.test[HNil, HNil]
      BSort.test[_1 :: HNil, _1 :: HNil]
      BSort.test[_0 :: _1 :: HNil, _0 :: _1 :: HNil]
    }
  }

  def printType[A](value: A)(using typename: TypeName[A]): Unit = println(typename.value.replace("com.meineliebe.lox.typelevel.hlist", "").replace("com.meineliebe.lox.typelevel.", ""))

}
