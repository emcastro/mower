package com.mowitnow

object CoordinateSystem {
  /** Type of position (abscissae and ordinates) */
  type Pos = Int

  def parse(s: String): Pos = {
    // TODO parse error
    Integer.parseUnsignedInt(s)
  }
}

import CoordinateSystem._

case class Point(x: Pos, y: Pos)

case class Program
(
  lawnTopRight: Point,
  mowerPrograms: Seq[MowerProgram]
)

object Command extends Enumeration {
  type Command = Value
  val A, G, D = Value
}
import Command._

object Direction extends Enumeration {
  type Direction = Value
  val N, E, W, S = Value
}
import Direction._

case class MowerProgram
(
  startPosition: Point,
  startDirection: Direction,
  commands: Seq[Command]
)