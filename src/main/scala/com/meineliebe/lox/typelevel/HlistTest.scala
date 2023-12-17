package com.meineliebe.lox.typelevel

import org.tpolecat.typename.{typeName, TypeName}
import shapeless3.deriving.*

import scala.annotation.targetName
import scala.collection.IndexedSeqView.Concat

def printType[A](value: A)(using typename: TypeName[A]): String = typename.value

val bsort = BSort.apply[_5 :: _1 :: _2  :: HNil]

@main
def main(): Unit = {
  println(printType(bsort).replace("com.meineliebe.lox.shapeless.", "").replace("HlistTest$package.", ""))
}
