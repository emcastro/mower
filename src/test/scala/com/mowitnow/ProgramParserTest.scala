package com.mowitnow

import com.mowitnow.Command._
import org.scalatest.FunSpec

class ProgramParserTest extends FunSpec {

  describe("ProgramParse") {
    it("parses correct program") {
      val program =
        """10 10
          |1 2 N
          |GDA
          |3 4 W
          |ADGADAD
          |5 6 E
          |A
          |7 8 S
          |GGGG
        """.stripMargin

      val model = Program(
        lawnTopRight = Point(10, 10),
        mowerPrograms = Seq(
          MowerProgram(
            Point(1, 2),
            Direction.N,
            Seq(G, D, A)
          ),
          MowerProgram(
            Point(3, 4),
            Direction.W,
            Seq(A, D, G, A, D, A, D)
          ),
          MowerProgram(
            Point(5, 6),
            Direction.E,
            Seq(A)
          ),
          MowerProgram(
            Point(7, 8),
            Direction.S,
            Seq(G, G, G, G)
          )
        )
      )

      assert(ProgramParser(program) == model)
    }
  }

}
