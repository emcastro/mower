package com.mowitnow

import java.io.{ByteArrayOutputStream, PrintStream}

import org.scalatest.FunSpec

import scala.io.Source

class MainTest extends FunSpec {

  def runMain(programResource: String): String = {
    val m = new ProgramRunner{}

    val outBuffer = new ByteArrayOutputStream()
    val outStream = new PrintStream(outBuffer)
    m.run(
      Source.fromURL(classOf[MainTest].getResource(programResource)),
      outStream
    )
    outStream.close()
    outBuffer.toString.trim
  }

  describe("The program") {

    it("runs the test program correctly") {
      assert(runMain("lawn.mow") ==
        """1 3 N
          |5 1 E
        """.stripMargin.trim)
    }

    it("runs ") {
      assert(runMain("ravingmower.mow") == "5 0 S")
    }

  }
}
