package com.meineliebe.lox

import java.util.Scanner
import scala.annotation.tailrec
import scala.io.Source
import scala.util.CommandLineParser.FromString
import scala.util.CommandLineParser.FromString.given

var hadError = false

def runFile(path: String): Unit = {
  run(Source.fromResource(path).mkString)
  if (hadError)
    System.exit(65)
}

@tailrec
def runPrompt(): Unit = {
  println("> ")
  val line = scala.io.StdIn.readLine()
  if (line == null) {
    ()
  } else {
    run(line)
    hadError = false
    runPrompt()
  }
}

def run(source: String): Unit = {
  val scanner = new Scanner(source)
  val tokens = scanner.tokens()
  tokens.forEach(println)
}

def error(line: Int, message: String): Unit =
  report(line, "", message)

def report(line: Int, where: String, message: String): Unit = {
  System.err.println(
    s"[line $line] Error $where: $message"
  )
  hadError = true
}

@main
def main(args: String*): Unit =
  if (args.length > 1) {
    println("Lox usage: jlox [script]")
    System.exit(64)
  } else if (args.length == 1)
    runFile(args(0))
  else
    runPrompt()
