package InterviewBit

import scala.collection.mutable

/**
  * Given numRows, generate the first numRows of Pascal’s triangle.
  * *
  * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from previous row R - 1.
  *
  * Example:
  *
  * Given numRows = 5,
  *
  * Return
  *
  * [
  * [1],
  * [1,1],
  * [1,2,1],
  * [1,3,3,1],
  * [1,4,6,4,1]
  * ]
  *
  */
object PascalsTriangle extends App {
  // Memoized
  def generate(A: Int): Array[Array[Int]] = {
    val memo = new mutable.HashMap[Int, Array[Int]]()

    def go: Int => Array[Int] = {
      case 0 =>
        Array()
      case 1 =>
        Array(1)
      case 2 =>
        Array(1, 1)
      case c =>
        memo.getOrElseUpdate(c, {
          val newrow = 1 +: (1 until c - 1).toArray.map { i => go(c - 1)(i) + go(c - 1)(i - 1) } :+ 1
          memo.update(c, newrow)
          newrow
        })
    }

    (1 to A).map { r: Int =>
      go(r)
    }.toArray
  }

  println(generate(5000).map(a => println(a.map(print))))

  // Regular version
  def slowGenerate(A: Int): Array[Array[Int]] = {
    def go(c: Int): Array[Int] = {
      if (c == 1)
        Array(1)
      else if (c == 2)
        Array(1, 1)
      else {
        1 +: (1 until c - 1).toArray.map { i => go(c - 1)(i) + go(c - 1)(i - 1) } :+ 1
      }
    }

    (1 to A).map { r: Int =>
      go(r)
    }.toArray
  }
}


