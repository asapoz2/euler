package euler

import scala.collection.mutable.MutableList

object currency {
  val coins = List(1,2,5,10,20,50,100,200)
  //val coins = List(1,2,5,10,20)
  def main(args: Array[String]) = {
    val make5 = waysToMakeWith(200,7)
    println("can make num in ways: "+make5)
  }
  def waysToMakeWith(what: Int, coinIdx: Int) :Int={
    if(coinIdx==0){
      println(what+" coins of value: 1")
      1
    }else{
      var count = 0
      val fullCoins = (what/coins(coinIdx)).round
      for(i<-0 to fullCoins){
        print(i+" coins of value: "+coins(coinIdx)+" ")
        count += waysToMakeWith(what-(i*coins(coinIdx)),coinIdx-1)
      }
      count
    }
  }
  def howManyTillFull(coinVal: Int, full: Int)=(full/coinVal).round
}