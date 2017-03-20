package euler

import scala.collection.mutable.MutableList

object primeperm {
  def main(args: Array[String]): Unit = {
    //println(EulerUtil.combinationsWithLenUsing(4, EulerUtil.digitsOf(1487).toIndexedSeq))
//    println(findEquid( EulerUtil.combinationsWithLenUsing(
//        4, EulerUtil.digitsOf(1487).toIndexedSeq).filter(EulerUtil.isPrime(_)) ))
    val allnums = (1112 to 9999).toList
    val results = allresults(allnums, List[List[Int]]())
    println(results)
//    println(EulerUtil.combinationsWithLenUsing(
//          4, EulerUtil.digitsOf(1112).toIndexedSeq))
  }
  def allresults(allnums: List[Int],resultCat: => List[List[Int]]): List[List[Int]]={
    if(allnums.length<1){
      resultCat
    }else{
      val target = allnums.head
      println(target)
      val perms = EulerUtil.combinationsWithLenUsing(
          4, EulerUtil.digitsOf(target).toIndexedSeq)
      val goodperms = perms.filter(EulerUtil.isPrime(_)).distinct
      val result = findEquid(goodperms, List[List[Int]]())
      
      allresults(allnums.filter(!perms.contains(_)),List.concat(resultCat,result))
    }
  }
  def findEquid(combos: MutableList[Int], endConcat: List[List[Int]]): List[List[Int]] = {
    if (combos.length <= 2) {
      endConcat
    } else {
      val result = new MutableList[List[Int]]
      val target = combos.head
      for (i <- 1 until combos.length) {
        val curr = combos(i)
        val dif = curr - target
        if (combos.contains(curr + dif)) {
          result += List(target, curr, curr + dif)
        }
      }
      findEquid(combos.drop(1),List.concat(result,endConcat))
    }
  }
  def distinctDigs(digs: List[Int]){
    digs.distinct.length == digs.length
  }
}