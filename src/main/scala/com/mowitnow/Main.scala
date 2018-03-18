package com.mowitnow

import java.io.{File, PrintStream}

import org.rogach.scallop.{ScallopConf, ScallopOption}

import scala.io.{BufferedSource, Source}

object Main extends App with ProgramRunner {

  class Conf extends ScallopConf(args) {
    val input: ScallopOption[File] = trailArg("program", "Mower program file (when omitted, the program is taken from stdin)", required = false)
    validateFileExists(input)
    verify()
  }

  val conf = new Conf

  val inStream: BufferedSource = conf.input.toOption match {
    case None => Source.fromInputStream(System.in)
    case Some(input) => Source.fromFile(input)
  }
  val outStream = Console.out

  run(inStream, outStream)
}


trait ProgramRunner {

  def run(inStream: BufferedSource, outStream: PrintStream): Unit = {

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
      outStream.println(s"$x $y $d")
    }
  }

}
