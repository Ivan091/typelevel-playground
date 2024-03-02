package com.meineliebe.typelevel

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import org.tpolecat.typename.TypeName

import scala.annotation.tailrec

trait TestBase extends AnyWordSpec with should.Matchers {

  private val pattern = """(.*)(Succ\[_(\d*)\])(.*)""".r

  def printType[A](value: A)(using typename: TypeName[A]): Unit = {

    val x = typename.value
      .replace("com.meineliebe.lox.typelevel.hlist", "")
      .replace("com.meineliebe.lox.typelevel.", "")
      .replace("\n", "")
      .replace("Core$package.", "")

    @tailrec
    def loop(s: String): String = {
      s match {
        case pattern(prefix, test, d, suffix) =>
          loop(s"${prefix}_${d.toInt + 1}$suffix")
        case _ => s
      }
    }

    println(loop(x))
  }
}
