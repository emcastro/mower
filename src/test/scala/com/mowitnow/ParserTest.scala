package com.mowitnow

import org.scalatest.FunSpec

class ParserTest extends FunSpec {

  describe("The parser") {

    it("parses split lines and words on EOL and whitespaces") {
      assert(Parser.parse(
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
      assert(Parser.parse(" A B\t  C   \nD  F") ==
        Seq(
          Seq("A", "B", "C"),
          Seq("D", "F")
        ))
    }

    it("ignores trailing lines only") {
      assert(Parser.parse(
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
      assert(Parser.parse("") == Seq.empty)
      assert(Parser.parse(" ") == Seq.empty)
      assert(Parser.parse("\n") == Seq.empty)
      assert(Parser.parse("\n ") == Seq.empty)
      assert(Parser.parse(" \n ") == Seq.empty)
    }
  }

}
