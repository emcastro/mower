package com.mowitnow

import com.mowitnow.Direction._
import org.scalatest.FunSpec

class basicTypesTest extends FunSpec {

  describe("Direction") {
    it("returns to self when turning four times") {
      for (d <- Direction.values) {
        assert(turnLeft(turnLeft(turnLeft(turnLeft(d)))) == d)
        assert(turnRight(turnRight(turnRight(turnRight(d)))) == d)
      }
    }

    def checkAllVisited(turn: Direction => Direction): Any = {
      var visited = Set.empty[Direction]
      (0 to 4).foldLeft(N) {
        (d, _) =>
          visited += d
          turn(d)
      }
      assert(visited.size == 4)
    }

    it("visits each values when turning four times") {
      checkAllVisited(turnLeft)
      checkAllVisited(turnRight)
    }
  }

  describe("Point") {
    it("returns to self when drawing a square") {
      assert(Point(0,0).goEast.goSouth.goWest.goNorth == Point(0,0))
    }

    it("visits the four corner of a square") {
      var visited = Seq.empty[Point]
      val last = Seq(N, E, S, S, W).foldLeft(Point(0,0)) {
        (p, direction) => {
          visited = visited :+ p
          p.go(direction)
        }
      }
      assert(last == Point(0, -1))
      assert(visited == Seq(
        Point(0,0),
        Point(0,1),
        Point(1,1),
        Point(1,0),
        Point(1, -1)
      ))
    }
  }

}