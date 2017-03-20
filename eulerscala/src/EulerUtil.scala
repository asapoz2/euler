package euler

import scala.collection.mutable.MutableList
import scala.collection.mutable.ArrayBuffer

object EulerUtil {
  def isPrime(n: Int) = (n > 1) && (!((2 until n - 1) exists (n % _ == 0)))
  def isPrime(n: Long) = (n > 1) && (!((2L until n - 1) exists (n % _ == 0)))
  def isPrimeNoEvens(n: Long) = (n > 1) && (!((3L until n - 1 by 2) exists (n % _ == 0)))
  def isSquare(n: Int) = math.sqrt(n).isWhole()
  def isPrime(n: BigInt) = (n > 1) && (!((BigInt(2) until n - 1) exists (n % _ == 0)))
  def digitsOf(n: Int) = {
    var curr = n
    val digs = new MutableList[Int]
    while (curr > 0) {
      digs.+=(curr % 10)
      curr /= 10
    }
    digs.toList.reverse
  }
  def digsOf(n: BigInt) = {
    var curr = n
    val digs = new MutableList[Int]
    while (curr > 0) {
      digs.+=((curr % 10).toInt)
      curr /= 10
    }
    digs.toList.reverse
  }
  def digsOf(n: Long) = {
    var curr = n
    val digs = new MutableList[Int]
    while (curr > 0) {
      digs.+=((curr % 10).toInt)
      curr /= 10
    }
    digs.toList.reverse
  }
  def digitsOf(n: BigInt) = {
    var curr = n
    val digs = new MutableList[BigInt]
    while (curr > 0) {
      digs.+=(curr % 10)
      curr /= 10
    }
    digs.toList.reverse
  }
  def toDig(list: List[Int]): Int = {
    var result = 0
    for (i <- 0 until list.length) {
      result = 10 * result + list(i)
    }
    result
  }
  def intLength(n: Int) = {
    (Math.log10(n) + 1).toInt
  }
  def intLength(n: Long) = {
    (Math.log10(n) + 1).toLong
  }

  def intLength(n: BigInt) = {
    n.toString().length()
  }
  def pandigitalsoflen(n: Int) = {
    appendAllCombos(0, (1 to n))
  }
  def pandigitalsWithZero(n: Int) = {
    appendAllCombos(0, (0 to n))
  }
  def appendAllCombos(start: Int, rest: IndexedSeq[Int]): MutableList[Int] = {
    var list = new MutableList[Int]
    if (rest.length == 1) {
      list += (start * 10 + rest.head)
      list
    } else {
      for (i <- 0 until rest.length) {
        list = MutableList.concat(list,
          appendAllCombos(start * 10 + rest.apply(i), rest.filter(_ != rest(i))))
      }
      list
    }
  }
  def combinationsWithLenUsing(endLen: Int, using: IndexedSeq[Int]) = {
    def appendAllCombosTill(endLen: Int, start: Int, rest: IndexedSeq[Int], currLen: Int): MutableList[Int] = {
      var list = new MutableList[Int]
      if (currLen >= endLen - 1) {
        for (i <- 0 until rest.length) {
          list += start * 10 + rest.apply(i)
        }
        list
      } else {
        for (i <- 0 until rest.length) {
          list = MutableList.concat(list,
            appendAllCombosTill(endLen, start * 10 + rest.apply(i), rest.diff(List(rest(i))), currLen + 1))
        }
        list
      }
    }
    appendAllCombosTill(endLen, 0, using, 0)
  }
  def calcNextPrime(n: Long, primesSoFar: ArrayBuffer[Long]) = {
    var curr = n + 2
    while (!EulerUtil.isPrime(curr)) {
      curr += 2
    }
    primesSoFar += curr
    //println(curr)
    curr
  }
  def reverseDigs(n: Long)= {
    def reverseDigs(num: Long, numSoFar: => Long): Long = {
      if(num<=0){
        numSoFar
      }else {
        val ten = math.pow(10, EulerUtil.intLength(num) - 1).toLong
        reverseDigs(num / 10, numSoFar + ((num%10) * ten))
      }
    }
    reverseDigs(n,0)
  }

}