package euler

object tripenthex {
  def main(args: Array[String]): Unit = {
    //println(findtph((1,1,1)))
    
  }
  def findtph(tuple: (BigInt,BigInt,BigInt)): BigInt={
    val applied = applyFuncs(tuple)
    if(applied._1.equals(applied._2) && applied._3.equals(applied._1)&& !applied._1.equals(1) && !applied._1.equals(40755)){
      applied._1
    }else{
      findtph(nextTuple(tuple,applied))
    }
  }
  def nextTuple(tuple: (BigInt,BigInt,BigInt),applied: (BigInt,BigInt,BigInt)): (BigInt,BigInt,BigInt)={
    if(applied._1<=applied._2 && applied._1<=applied._3){
      (tuple._1+1,tuple._2,tuple._3)
    }else if(applied._2<=applied._1 && applied._2<=applied._3){
       (tuple._1,tuple._2+1,tuple._3)
    }else{
      (tuple._1,tuple._2,tuple._3+1)
    }
  }
  def applyFuncs(tuple: (BigInt,BigInt,BigInt))={
    (triAt(tuple._1),pentAt(tuple._2),hexAt(tuple._3))
  }
  def triAt(n: BigInt)={
    n*(n+1)/2
  }
  def pentAt(n: BigInt)={
    n*(3*n-1)/2
  }
  def hexAt(n: BigInt)={
    n*(2*n-1)
  }
}