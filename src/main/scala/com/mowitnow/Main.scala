package com.mowitnow

import java.io.File

import org.rogach.scallop.{ScallopConf, ScallopOption}

import scala.io.Source

object Main extends App {

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

  val lawn = Lawn(
    top = model.lawnTopRight.y,
    right = model.lawnTopRight.x,
    bottom = 0,
    left = 0
  )

  for (program <- model.mowerPrograms) {
    val mowerStart = Mower(
      lawn = lawn,
      position = program.startPosition,
      direction = program.startDirection
    )

    val mowerEnd = program.commands.foldLeft(mowerStart) {
      (m, command) =>
        m.step(command)
    }

    val Mower(_, Point(x, y), d) = mowerEnd
    println(s"$x $y $d")
  }
}
