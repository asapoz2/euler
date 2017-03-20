package euler

import scala.collection.mutable.MutableList

object permmult {
  def main(args: Array[String]): Unit = {
    //println(sameDigs(125874,125874*2))
    var start = 125874
    while(!sameSixDigs(start)){
      start+=1
    }
    println(start)
    println(start*2)
    println(start*3)
    println(start*4)
    println(start*5)
    println(start*6)
    
  }
  def sameSixDigs(num: BigInt)={
    val dig1 = EulerUtil.digsOf(num)
    val digs6 = EulerUtil.digsOf(num*6)
    if(digs6.length>dig1.length){
      false
    }else{
      sameDigs(dig1,num*2) && sameDigs(dig1,num*3) && sameDigs(dig1,num*4) && sameDigs(dig1,num*5)&&
      sameDigs(digs6,num*5)
    }
  }
  def sameDigs(num1: List[Int],num2: BigInt)={
    num1.sorted.equals(EulerUtil.digitsOf(num2).sorted)
  }
}