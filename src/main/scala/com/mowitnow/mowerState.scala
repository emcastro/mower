package com.mowitnow

import com.mowitnow.Command.Command
import com.mowitnow.CoordinateSystem.Pos
import com.mowitnow.Direction.Direction

/**
  * The lawn, from the point of view of the mower.
  *
  * @param top    Top of the lawn
  * @param right  Right of the lawn
  * @param bottom Bottom of the lawn, usually 0
  * @param left   Left of the lawn, usually 0
  */
case class Lawn
(
  top: Pos,
  right: Pos,
  bottom: Pos,
  left: Pos
) {
  def contains(p: Point): Boolean = (
    (p.x >= left)
      && (p.x <= right)
      && (p.y >= bottom)
      && (p.y <= top)
    )
}

/**
  * The state of the mower (position, direction) and its perception of the lawn
  *
  * @param lawn      The perception of the lawn by the mower
  * @param position  Position of the lawn
  * @param direction Direction
  */
case class Mower
(
  lawn: Lawn,
  position: Point,
  direction: Direction
) //
{
  def step(command: Command): Mower = {
    import com.softwaremill.quicklens._

    command match {
      case Command.A => this.modify(_.position).using {
        oldPosition =>
          val newPosition = oldPosition.go(direction)
          if (lawn.contains(newPosition))
            newPosition
          else
            oldPosition
      }
      case Command.G => this.modify(_.direction).using(Direction.turnLeft)
      case Command.D => this.modify(_.direction).using(Direction.turnRight)
    }
  }
}
