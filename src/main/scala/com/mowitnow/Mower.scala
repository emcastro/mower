package com.mowitnow

import java.io.File

import org.rogach.scallop.{ScallopConf, ScallopOption}

import scala.io.Source

object Mower extends App {

  class Conf extends ScallopConf(args) {
    val input: ScallopOption[File] = trailArg("program", "Mower set program file", required = false)
    validateFileExists(input)
    verify()
  }

  val conf = new Conf

  val inStream = conf.input.toOption match {
    case None => Source.fromInputStream(System.in)
    case Some(input) => Source.fromFile(input)
  }

  val model = ProgramParser(inStream.mkString).consumeProgram()

  println(model)
}
