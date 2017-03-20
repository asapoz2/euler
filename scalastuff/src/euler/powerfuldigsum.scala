package euler

object powerfuldigsum {
  def main(args: Array[String]): Unit = {
    //println(digitalSum(BigInt("123456789")))
    val allvals = (1 to 100).par.map((x: Int) =>
      ((BigInt(1) to 100).map((y: BigInt) => digitalSum(y.pow(x))).
        max)).max
    println(allvals)
  }
  def digitalSum(x: BigInt)={
    var currx=x
    var sum=0L
    while(currx>0){
      sum += currx.%(10).toLong
      currx = currx/10
    }
    sum
  }
  
}