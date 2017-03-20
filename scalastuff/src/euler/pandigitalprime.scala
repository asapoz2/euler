package euler

import scala.collection.mutable.MutableList
import euler.EulerUtil._
//7652413
object pandigitalprime {
  def main(args: Array[String]): Unit = {
//    val fourprimes = pandigitalsoflen(9).filter(EulerUtil.isPrime(_))
//    println(fourprimes)
    println("4".toInt)
  }
  def pandigitalsoflen(n: Int)={
    appendAllCombos(0,(1 to n))
  }
  def appendAllCombos(start: Int, rest: IndexedSeq[Int]): MutableList[Int]={
    var list = new MutableList[Int]
    if(rest.length==1){
      list+=(start*10+rest.head)
      list
    }else{
      for(i<-0 until rest.length){
        list=MutableList.concat(list,
            appendAllCombos(start*10+rest.apply(i),rest.filter(_!=rest(i))))
      }
      list
    }
  }
}