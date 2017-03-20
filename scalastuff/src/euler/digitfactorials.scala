package euler

import scala.collection.mutable.MutableList

object digitfactorials {
  //val digfacts = (0 to 9).map(factorial(_))
  val digfacts = Array(1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880)
  //145, 25, 1
  //120,
  //5
  //125
  //123
  
  def main(args: Array[String]) = {
    //println(factorial(5))
    //println(digitsOf(120))
    
//    println(isDigFactorial(120))
//    println(isDigFactorial(145))
    
    //println((3 to 40730).par.filter(isDigFactorial(_)).par.sum)
    
    //println(digfacts)
    //val threedigfacts = digfacts.filter(_<1000)
    
//    val facts = for(i<-1 to 6;j<-0 to 6; k<-0 to 6; 
//    val sum= digfacts(i)+digfacts(j)+digfacts(k); if sum == (100*i +10*j+k)) yield sum
//    println(facts)
    
    var result=0
    for (i<-10 to 2540161) {
      var  sumOfFacts = 0;
      var number = i;
      while (number > 0) {
        val d = number % 10
        number /= 10;
        sumOfFacts += digfacts(d.toInt)
      }
 
      if (sumOfFacts == i) {
        println("found one "+i)
          result += i;
      }
    }
    println(result)
    
  }
  def factorial(n: Int)={
    def helper(sofar: Int, rest: Int):Int={
      if(rest==1){
        sofar
      }else{
        helper(sofar*rest,rest-1)
      }
    }
    if(n==0){
      0
    }
    if(n==1){
      1
    }
    helper(1,n)
  }
  def digitsOf(n: Int)={
    var curr = n
    val digs = new MutableList[Int]
    while(curr>0){
      digs.+=(curr%10)
      curr/=10
    }
    digs
  }
  def isDigFactorial(n: Int)={
    //n==(digitsOf(n).map(digfacts(_)).sum)
    val digs = digitsOf(n)
    n==((for(i<-0 until digs.length) yield digfacts(digs(i))).sum)
  }
  
  
}