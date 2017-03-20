package euler

import scala.collection.mutable.ArrayBuffer

object amacable {
  def main(args: Array[String])={
//    val thedev = divisorsum(220)
//    println("divisors are "+thedev)
    
//    for(x<-1 to 10000) { 
//      val result = divisorsum(x)
//      
//    }
//    def pred(n: Int) = (divisorsum(divisorsum(n))==n && divisorsum(n)!=n)
//    val amicables = (1 to 10000).filter(pred(_))
//    println(amicables)
//    val sum = amicables.sum
//    
//    println("sum of divisors is "+sum)
    println(EulerUtil.isPrime(3709))
    println(EulerUtil.isPrime(7093))
    println(EulerUtil.isPrime(5473))
    println(EulerUtil.isPrime(3547))
  }
  def divisors(num: Int) = (1 to (num/2)).filter(num%_==0)
  def divisorsum(num: Int) = (1 to (num/2)).filter(num%_==0).sum
}