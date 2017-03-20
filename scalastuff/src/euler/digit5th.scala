package euler

import scala.collection.mutable.MutableList

object digit5th {
  val limit4=32805
  val limit=295245
  val digs5 = (0 to 9).map(math.pow(_, 5).toInt)
  val digs4 = (0 to 9).map(math.pow(_, 4).toInt)
  def main(args: Array[String]): Unit = {
    val result = (2 to limit).filter(sumArePowers(_)).sum
    println(result)
  }
  def sumArePowers(n: Int)={
    n==digitsOf(n).map(digs5(_)).sum
  }
  def digitsOf(n: Int)={
    var curr = n
    val digs = new MutableList[Int]
    while(curr>0){
      digs.+=(curr%10)
      curr/=10
    }
    digs.toList.reverse
  }
}