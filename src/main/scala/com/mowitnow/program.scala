package com.mowitnow

import com.mowitnow.Command.Command
import com.mowitnow.Direction.Direction


case class Program
(
  lawnTopRight: Point,
  mowerPrograms: Seq[MowerProgram]
)


case class MowerProgram
(
  startPosition: Point,
  startDirection: Direction,
  commands: Seq[Command]
)

object Command extends Enumeration {
  type Command = Value
  val A, G, D = Value
}
