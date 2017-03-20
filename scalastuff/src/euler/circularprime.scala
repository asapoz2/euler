package euler

import scala.collection.mutable.MutableList

object circularprime {
  val primes = new Array[Boolean](1000000)
  def main(args: Array[String]): Unit = {
    for(i<- 2 to 999999){
      primes(i)=isPrime(i)
    }
    var ctr = 0
    for(i<- 2 to 999999 by 2){
      if(allDigsPrime(i)){
        ctr+=1
        println(i)
      }
    }
    println(ctr)
  }
  def isPrime(n: Int) = (n>0)&& (!((2 until n - 1) exists (n % _ == 0)))
  def digitsOf(n: Int)={
    var curr = n
    val digs = new MutableList[Int]
    while(curr>0){
      digs.+=(curr%10)
      curr/=10
    }
    digs.toList.reverse
  }
  def toDig(list: List[Int]): Int={
    var result=0
    for(i<-0 to list.length){
      result=10*result+i
    }
    result
  }
  def allDigsPrime(n:Int): Boolean={
    if(!primes(n)){
        false
    }else{
      var rotated=n
      val rotations = for(i<-1 until intLength(n)) yield{
        rotated = rotate(rotated);rotated
      }
      if(rotations.filter(primes(_)).length==rotations.length){
        true
      }else{
        false
      }
    }
  }
  def rotate(n: Int)={
    val length = intLength(n)
    if(length==1){
      n
    }
    val lastdig = n%10
    (n/10)+(math.pow(10,length-1)*lastdig).toInt
  }
  def intLength(n: Int)={
    (Math.log10(n)+1).toInt
  }

}