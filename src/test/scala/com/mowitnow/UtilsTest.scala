package com.mowitnow

import org.scalatest.FunSpec

class UtilsTest extends FunSpec {

  describe("Utils.generateWhile") {

    it("generates sequences") {
      var x = 0

      val generated =
        Utils.generateWhile(x < 6) {
          x += 1
          x.toString
        }

      assert(generated == Seq("1", "2", "3", "4", "5", "6"))
    }

    it("does not generate element when not needed") {
      val never = false

      var failed = false
      val result = Utils.generateWhile(never) {
        failed = true
      }
      assert(result == Seq.empty)
      assert(!failed)
    }
  }

}
