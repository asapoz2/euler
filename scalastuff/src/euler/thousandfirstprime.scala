package euler

import scala.collection.mutable.ArrayBuffer

object thousandfirstprime {
  val primesSoFar = new ArrayBuffer[Long]
  def main(args: Array[String]): Unit = {
    primesSoFar += 2
    primesSoFar += 3
    var currPrime = 3L
    var len = 2
    while (len<10001 ) {
      println(len)
      currPrime = calcNextPrime(currPrime)
      len+=1
    }
    println(currPrime)
//    println(EulerUtil.isPrimeNoEvens(13L))
//    println(EulerUtil.isPrimeNoEvens(15L))
  }
  def calcNextPrime(n: Long) = {
    var curr = n + 2
    while (!EulerUtil.isPrimeNoEvens(curr)) {
      curr += 2
    }
    primesSoFar += curr
    //println(curr)
    curr
  }
}