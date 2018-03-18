package com.mowitnow

import org.scalatest.FunSpec

class mowerStateTest extends FunSpec {

  val top = 10
  val right = 5
  val bottom = -3
  val left = 0

  val lawn = Lawn(top, right, bottom, left)

  describe("Lawn") {
    it("can tell if a point is inside of outside") {
      assert(lawn.contains(Point(0, 0)))

      assert(!lawn.contains(Point(0, top + 1))) // above top
      assert(!lawn.contains(Point(right + 1, 0))) // farther that right
      assert(!lawn.contains(Point(0, bottom - 1))) // below bottom
      assert(!lawn.contains(Point(left - 1, 0))) // farther that left

      assert(lawn.contains(Point(right, top)))
      assert(lawn.contains(Point(right, bottom)))
      assert(lawn.contains(Point(left, top)))
      assert(lawn.contains(Point(left, bottom)))
    }
  }

  describe("Mower") {
    val mowerEastBorder = Mower(lawn, Point(right, 0), Direction.E)

    it("does not move when trying to get out of lawn") {
      assert(mowerEastBorder.step(Command.A) == mowerEastBorder) // no move
    }

    it("still can turn when in the border of the lawn") {
      assert(mowerEastBorder.step(Command.G) == mowerEastBorder.copy(direction = Direction.N)) // we turn
      assert(mowerEastBorder.step(Command.D) == mowerEastBorder.copy(direction = Direction.S)) // we turn
    }

    val mowerEast = Mower(lawn, Point(0, 0), Direction.E)

    it("move when going forward") {
      assert(mowerEast.step(Command.A) == mowerEast.copy(position = Point(1, 0)))
    }
  }

}
