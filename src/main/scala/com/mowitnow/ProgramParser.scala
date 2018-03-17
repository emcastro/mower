package com.mowitnow

import com.mowitnow.Command.Command
import com.mowitnow.CoordinateSystem.Pos
import com.mowitnow.Direction.Direction
import com.mowitnow.Utils.generateWhile

import scala.language.postfixOps

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
      consumePoint(), // TODO ask the customer if it is really a single digit, or simply a non-negative number
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
    assert(isEOL)

    currentLine += 1
    currentWord = 0
  }

  def consumeEOF(): Unit = {
    // TODO parse error
    assert(isEOF)
  }

  def isEOL: Boolean = currentWord == text(currentLine).size

  def isEOF: Boolean = currentLine == text.size

  ///////

  implicit class ParserModality[A](a: A) {
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
  def apply(text: String): ProgramParser = new ProgramParser(SsvParser.ssvParse(text))
}


