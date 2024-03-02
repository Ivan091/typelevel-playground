package com.meineliebe.typelevel.hlist

import com.meineliebe.typelevel.*
import com.meineliebe.typelevel.hlist.=== as hleq
import com.meineliebe.typelevel.hlist.BSort.given

class HListTest extends TestBase {

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
}
