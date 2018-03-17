package com.mowitnow

import scala.collection.mutable

object Utils {

  def generateWhile[A](p: => Boolean)(elem: => A): Seq[A] = {
    val buffer = mutable.Buffer[A]()
    while (p) {
      buffer += elem
    }
    buffer.toIndexedSeq
  }

}
