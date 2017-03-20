package euler

object evenfib {
  def main(args: Array[String]): Unit = {
    var fib1 = 1
    var fib2 = 2
    var sumeven=2
    while(fib2<4000000){
    //while(fib2<89){
      val next = fib1 + fib2
      fib1 = fib2
      fib2 = next
      if(fib2.&(1)==0){
        sumeven+=fib2
      }
    }
    println(sumeven)
  }
}