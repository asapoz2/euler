package euler

object factorialdigsum {
  def main(args: Array[String])={
    println("factorial digit sum")
    val fac10 = (BigInt(1) to BigInt(100)).par.reduceLeft(_*_)
    println("fac10 is"+fac10)
    val result = fac10.toString().map(_-'0').sum
    println("result is "+result)
  }
}