package euler

import scala.collection.mutable.ArrayBuffer

object consecprimesum {
  val primesSoFar = new ArrayBuffer[Long]
  def main(args: Array[String]): Unit = {
    primesSoFar+=2
    primesSoFar+=3
    var currPrime=3L
    while(currPrime<(200)){
    //while(currPrime<(1000000/15)){
      currPrime= EulerUtil.calcNextPrime(currPrime, primesSoFar)
    }
    println(getLargestStartingAt(0,100))
    //println((0 until primesSoFar.length-16).par.map(getLargestStartingAt(_,1000000)).maxBy(_._2))
    
    //println(sumsofar)
  }
  def getLargestStartingAt(primeIdx: Int,lim: Long)={
    println(primeIdx)
    var currIdx = primeIdx+1
    var sumsofar = primesSoFar(primeIdx)
    
    while(sumsofar<lim){
      sumsofar+=primesSoFar(currIdx)
      currIdx+=1
    }
    currIdx-=1
    sumsofar-=primesSoFar(currIdx)
    currIdx-=1
    var lastnotprime= !EulerUtil.isPrime(sumsofar)
    while(lastnotprime && currIdx>=0){
      sumsofar-=primesSoFar(currIdx)
      currIdx-=1
      lastnotprime= !EulerUtil.isPrime(sumsofar)
    }
    if(lastnotprime){
      (0,0)
    }
    currIdx+=1
    (sumsofar,currIdx-primeIdx)
  }
  
//  def calcNextPrime(n: Long)={
//    var curr = n+2
//    while(!EulerUtil.isPrime(curr)){
//      curr+=2
//    }
//    primesSoFar+=curr
//    //println(curr)
//    curr
//  }
}