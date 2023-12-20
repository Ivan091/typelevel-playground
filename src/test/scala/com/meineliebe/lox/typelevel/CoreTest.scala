package com.meineliebe.lox.typelevel

import com.meineliebe.lox.typelevel.{< as lt, <= as ltq, == as teq}
import com.meineliebe.lox.typelevel.Dec.given
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import org.tpolecat.typename.TypeName

import scala.runtime.stdLibPatches.Predef.summon

class CoreTest extends AnyWordSpec with should.Matchers {

  def printType[A](value: A)(using typename: TypeName[A]): Unit = println(typename.value.replace("com.meineliebe.lox.typelevel.hlist", "").replace("com.meineliebe.lox.typelevel.", ""))


  "Dec" in {
    Dec.test[_9, _8]
    Dec.test[_1, _0]
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

  "Apply" in {
    val x = Apply[_Dec, _1]
    val y = Dec[_1]
    teq[x.R, y.R]
  }
}
