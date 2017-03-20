package euler

import scala.collection.mutable.MutableList

object pentsumanddiff {
  val list = new MutableList[Int]
  def main(args: Array[String]): Unit = {
    //println((1 to 200).map(pentAt(_)).map(isPent(_)))
//    list+=1
//    var idx=2
//    var found = IndexedSeq.empty[Int]
//    while( found.length==0){
//      val pent = pentAt(idx)
//      val sofar = (0 until idx-1).map(list(_))
//      found = foundSumAndDiff(pent,sofar)
//      list+=pent
//      idx+=1
//    }
//    println(idx)
//    println(found)
    
    //idx 2168
    //real idx 2167
    //found 1560090
    //      7049252
    //      7042750
    //println(pentAt(2167))
    //println(7049252-1560090)
    //println(isPent(7042750-1560090))
    //println(isPent(7042750+1560090))
    println(7042750-1560090)
  }
  //sofar.map((x:Int)=>(pent-x,pent+x)).
  //  filter((tuple: (Int,Int))=>(isPent(tuple._1)&&isPent(tuple._2)))
  def foundSumAndDiff(pent: Int,sofar: IndexedSeq[Int])={
    sofar.filter((x:Int)=>(isPent(pent-x)&&isPent(pent+x)))
  }
  
  def pentAt(n: Int)={
    n*((3*n)-1)/2
  }
  def isPent(n: Int)={
    val dub = n.toDouble
    val top = (math.sqrt(24*dub+1)+1)/6
    top.isWhole()
  }
}