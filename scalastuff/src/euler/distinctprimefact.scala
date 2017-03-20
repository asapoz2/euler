package euler

import scala.collection.mutable.MutableList

object distinctprimefact {
  def main(args: Array[String]): Unit = {
//    println(hasdpf(15,2))
//    println(hasdpf(14,2))
//    println(hasdpf(11,2))
    println(findConsecutive(4))
  }
  def findConsecutive(numFact: Int)={
    var start =45694
    var continue=true
    while(continue){
      val startPrime = hasdpf(start,numFact)
      val secPrime = hasdpf(start+1,numFact)
      val thirdPrime = hasdpf(start+2,numFact)
      val fourthPrime = hasdpf(start+3,numFact)
      continue= !(startPrime&&secPrime&&thirdPrime&&fourthPrime)
      if(fourthPrime){
        start+=3
      }else if(thirdPrime){
        start+=2
      }else if(secPrime){
        start+=1
      }else{
        start+=4
      }
    }
    start-3
  }
  def hasdpf(n: Int, numFact: Int) = {
    val factors = getFactorsOf(n)
    val factlen = factors.length
    factlen == numFact && factors.filter(!math.sqrt(_).isWhole).length == factlen
  }
  def getFactorsOf(n: Int)={
    //(2 to n/2).filter(n%_==0)
    val factors = new MutableList[Int]
    var curr = n
    for(i<-2 to n/2){
      if(curr%i==0){
        factors+=i
        while(curr%i==0){
          curr/=i
        }
      }
    }
    factors
  }
}