package euler

import scala.collection.mutable.HashMap
import scala.collection.immutable.TreeMap
import scala.collection.mutable.MutableList

object longestcollatz {
  val stoptimes=10
  val stopcol=100
  val collatzMap = new Array[Int](1000000)
  def main(args: Array[String]): Unit = {
    //println(findDrops(53).map(nToDrop(53,_)))
    var maxes = new MutableList[Int]
    var longs = new MutableList[Int]
    for(i<-128 to 1024){
      var list = new MutableList[Int]
      longs+=listTillOne(i,list)
      maxes += list.max
    }
    for(i<-0 until longs.length){
      println("("+(128+i)+","+longs.apply(i)+","+maxes.apply(i)+")")
    }
    val maxidx=longs.indexOf(longs.max)
    println("("+(128+maxidx)+","+longs.apply(maxidx)+","+maxes.apply(maxidx)+")")
  }
  def maxRevCollatz(n: BigInt) = {
  }
  def allCollatz(sofar: Vector[BigInt], start: BigInt)={
    if(start>stopcol){
      scala.collection.immutable.Vector.empty[BigInt]
    }
  }
  def nToDrop(n: BigInt, drop: BigInt)={
    Iterator.iterate(n)(_*2).takeWhile(_<=drop).toVector
  }
  def findDrops(n: BigInt) = {
    val timestwos = for (i <- 0 to stoptimes) yield n * BigInt(2).pow(i)
    timestwos.filter(canDrop(_))
  }
  def canDrop(n: BigInt) = {
    ((n - 1) % 3 == 0) && ((n - 1) / 3) % 2 != 0
  }

  def howLongTillOne(n: Int): Int = {
    if (n == 1) {
      1
    } else if (n < 201012868) {
      val atN = collatzMap.apply(n)
      if (atN != 0) {
        atN
      } else {
        val next = nextCollatz(n)
        val result = howLongTillOne(next) + 1
        collatzMap.update(n, result)
        result
      }
    } else {
      val next = nextCollatz(n)
      val result = howLongTillOne(next) + 1
      result
    }
  }
  def listTillOne(n: Int, list: MutableList[Int]): Int = {
    list += n
    if (n == 1) {
      1
    } else {
      val next = nextCollatz(n)
      val result = listTillOne(next, list) + 1
      collatzMap.update(n, result)
      result
    }

  }
  def nextCollatz(n: Int) = {
    if (n % 2 == 0) {
      n / 2
    } else {
      3 * n + 1
    }
  }
  def revCollatz(n: Int) = {
    if (n == 1) {
      n * 2
    } else if ((n - 1) % 3 == 0) {
      (n - 1) / 3
    } else {
      n * 2
    }
  }
  // 1      //3      //13    //15      //81    //769
  //&&n!=4 && n!=10&&n!=40 &&n!=46 && n!=244&& n!=2308
}