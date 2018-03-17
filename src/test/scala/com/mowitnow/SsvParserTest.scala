package com.mowitnow

import org.scalatest.FunSpec

class SsvParserTest extends FunSpec {

  describe("The parser") {

    it("parses split lines and words on EOL and whitespaces") {
      assert(SsvParser.ssvParse(
        """A B
          |1 2,3 4.5
          |z w""".stripMargin) ==
        Seq(
          Seq("A","B"),
          Seq("1", "2,3", "4.5"),
          Seq("z", "w")
        ))
    }

    it("ignores extra spaces") {
      assert(SsvParser.ssvParse(" A B\t  C   \nD  F") ==
        Seq(
          Seq("A", "B", "C"),
          Seq("D", "F")
        ))
    }

    it("ignores trailing lines only") {
      assert(SsvParser.ssvParse(
        """A B
          |
          |D E
          |
          |
        """.stripMargin) ==
        Seq(
          Seq("A", "B"),
          Seq(),
          Seq("D", "E")
        )
      )
    }

    it("parses empty inputs") {
      assert(SsvParser.ssvParse("") == Seq.empty)
      assert(SsvParser.ssvParse(" ") == Seq.empty)
      assert(SsvParser.ssvParse("\n") == Seq.empty)
      assert(SsvParser.ssvParse("\n ") == Seq.empty)
      assert(SsvParser.ssvParse(" \n ") == Seq.empty)
    }
  }

}
