package euler

object collatzallcomb {
  val stoptimes=10
  val left=100
  val collatzMap = new Array[Int](100)
  def main(args: Array[String]): Unit = {
    println(findDrops(53).map(nToDrop(53,_)))
  }
  def maxRevCollatz(n: BigInt) = {
  }
  def allCollatz(sofar: Vector[BigInt], start: BigInt)={
    
  }
  def nToDrop(n: BigInt, drop: BigInt)={
    Iterator.iterate(n)(_*2).takeWhile(_<=drop).toVector
  }
  def findDrops(n: BigInt) = {
    val timestwos = for (i <- 0 to stoptimes) yield n * BigInt(2).pow(i)
    timestwos.filter(canDrop(_))
  }
  def canDrop(n: BigInt) = {
    ((n - 1) % 3 == 0) && ((n - 1) / 3) % 2 != 0
  }
}