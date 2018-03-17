package com.mowitnow

import com.mowitnow.Command.Command
import com.mowitnow.CoordinateSystem.Pos
import com.mowitnow.Direction.Direction
import com.mowitnow.Utils.generateWhile

/**
  * Stateful object that parses lines of words and produce a MowerProgram
  *
  * @param text input text from SsvParser.ssvParse
  */
class ProgramParser(text: SsvParser.Text) {

  var currentLine = 0
  var currentWord = 0

  def consumePoint(): Point = Point(consumePos(), consumePos())

  def consumePos(): Pos = CoordinateSystem.parse(consumeWord())

  def consumeWord(): String = {
    // TODO parse error
    val result = text(currentLine)(currentWord)
    currentWord += 1
    result
  }

  def consumeDirection(): Direction = {
    // TODO parse error
    Direction.withName(consumeWord())
  }

  def consumeCommands(): Seq[Command] = {
    consumeWord().map(c => {
      // TODO parse error
      Command.withName(c.toString)
    })
  }

  def consumeMowerProgram(): MowerProgram = {
    MowerProgram(
      consumePoint(),
      consumeDirection() atEOL,
      consumeCommands() atEOL
    )
  }

  def consumeProgram(): Program = {
    Program(
      consumePoint() atEOL,
      generateWhile(!isEOF) {
        consumeMowerProgram()
      }
    ) atEOF
  }

  def consumeEOL(): Unit = {
    // TODO parse error
    // assert that there is nothing left on the line
    currentLine += 1
    currentWord = 0
  }

  def consumeEOF(): Unit = {
    // TODO parse error
    assert(isEOF)
  }

  def isEOF: Boolean = currentLine == text.size

  ///////

  implicit class ParserAssertion[A](a: A) {
    def atEOL: A = {
      consumeEOL()
      a
    }

    def atEOF: A = {
      consumeEOF()
      a
    }
  }

}

object ProgramParser {
  def apply(text: String): Program = new ProgramParser(SsvParser.ssvParse(text)).consumeProgram()
}


