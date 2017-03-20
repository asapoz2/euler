package euler

object truncatableprimes {
  def main(args: Array[String])={
//    println(isTruncatable(3797))
//    println(isTruncatable(3796))
    
//    val truncs = (10 to 10000).filter(isTruncatable(_))
//    println(truncs)
    
//    var ctr = 0
//    var curr = 10
//    while(ctr<11){
//      curr+=1
//      if(isTruncatable(curr)){
//        println("found one at "+ curr)
//        ctr+=1
//      }
//    }
    
    /*found one at 23
found one at 37
found one at 53
found one at 73
found one at 313
found one at 317
found one at 373
found one at 797
found one at 3137
found one at 3797
found one at 739397*/
    val sum = 23+37+53+73+313+317+373+797+3137+3797+739397
    println(sum)
  }
  def isPrime(n: Int) = (n>1)&& (!((2 until n - 1) exists (n % _ == 0)))
  def isTruncatable(n: Int): Boolean={
    if(isPrime(n)){
      if(n<10){
        true
      }else{
        val thestr = n.toString()
        isLeftTruncatable(thestr.substring(1, thestr.length()).toInt)&&
        isRightTruncatable(thestr.substring(0, thestr.length()-1).toInt)
      }
    }else{
      false
    }
  }
  def isLeftTruncatable(n: Int): Boolean={
    if(isPrime(n)){
      if(n<10){
        true
      }else{
        val thestr = n.toString()
        isLeftTruncatable(thestr.substring(1, thestr.length()).toInt)
      }
    }else{
      false
    }
  }
  def isRightTruncatable(n: Int): Boolean={
    if(isPrime(n)){
      if(n<10){
        true
      }else{
        val thestr = n.toString()
        isRightTruncatable(thestr.substring(0, thestr.length()-1).toInt)
      }
    }else{
      false
    }
  }
  
}