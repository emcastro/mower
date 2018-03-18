package com.mowitnow

import com.mowitnow.CoordinateSystem.Pos
import com.mowitnow.Direction.Direction


case class Point(x: Pos, y: Pos) {
  def goNorth = Point(x, y + 1)

  def goSouth = Point(x, y - 1)

  def goWest = Point(x - 1, y)

  def goEast = Point(x + 1, y)

  def go(d: Direction): Point = d match {
    case Direction.N => goNorth
    case Direction.S => goSouth
    case Direction.W => goWest
    case Direction.E => goEast
  }
}

object Direction extends Enumeration {
  type Direction = Value
  val N, E, S, W = Value

  val turnLeft = Map(
    N -> W,
    W -> S,
    S -> E,
    E -> N
  )

  val turnRight = Map(
    N -> E,
    E -> S,
    S -> W,
    W -> N
  )
}

object CoordinateSystem {
  /** Type of position (abscissae and ordinates) */
  type Pos = Int

  def parse(s: String): Pos = {
    // TODO parse error
    Integer.parseUnsignedInt(s)
  }
}
