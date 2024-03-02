package com.meineliebe.typelevel.math

import com.meineliebe.typelevel.{< as lt, _0, TestBase, *}
import org.scalatest.wordspec.AnyWordSpec

class MathTest extends TestBase {

  "sum" in {
    Sum.test[_0, _1, _1]
    Sum.test[_1, _0, _1]
    Sum.test[_0, _0, _0]
    Sum.test[_1, _1, _2]
    Sum.test[_1, _2, _3]
    Sum.test[_3, _4, _7]
    Sum.test[_5, _4, _9]
    Sum.test[_4, _5, _9]
  }

  "subtract" in {
    Subtract.test[_0, _0, _0]
    Subtract.test[_1, _0, _1]
    Subtract.test[_7, _0, _7]
    Subtract.test[_2, _1, _1]
    Subtract.test[_7, _5, _2]
    Subtract.test[_9, _4, _5]
  }

  "multiply" in {
    Multiply.test[_0, _0, _0]
    Multiply.test[_1, _0, _0]
    Multiply.test[_1, _1, _1]
    Multiply.test[_2, _1, _2]
    Multiply.test[_3, _3, _9]
    Multiply.test[_3, _2, _6]
    Multiply.test[_2, _3, _6]
  }

  "power" in {
    Power.test[_0, _0, _1]
    Power.test[_3, _2, _9]
    Power.test[_2, _3, _8]
  }

  "divide" in {
    Divide.test[_0, _4, _0]
    Divide.test[_2, _3, _0]
    Divide.test[_4, _2, _2]
    Divide.test[_2, _1, _2]
  }
}
