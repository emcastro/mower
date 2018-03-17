package com.mowitnow

object Parser {

  type Text = Seq[Line]
  type Line = Seq[String]

  /**
    * Parses a space separated value text. Trailing empty lines are ignored
    *
    * @param text The text to parse
    * @return The parsed text, as a sequence of lines (i.e. a sequence of strings)
    */
  def parse(text: String): Text = {
    val allLines =
      for (line <- text.split("\r?\n").toIndexedSeq)
        yield line.trim match {
          case "" => Seq.empty // String.split would returns a seq with an empty String instead of an empty seq.
          case trimmed => trimmed.split("\\s+").toVector
        }

    allLines.take(allLines.lastIndexWhere(_.nonEmpty) + 1) // remove trailing empty lines
  }


}
