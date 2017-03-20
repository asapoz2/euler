package euler

object numerspirals {
  def main(args: Array[String]): Unit = {
    val total = spiralTill(1001*1001)
    println(total)
  }
  def spiralTill(limit: Long)={
    var start: Long=2
    var size: Long=3
    var sum: Long=0
    while(start<limit){
      sum+=getFourCorners(start,size)
      start=getNextStart(start,size)
      size+=2
    }
    sum+1
  }
  def getNextStart(start: Long, size: Long)={
    val bleft = start+size-2
    bleft+(3*(size-1))+1
  }
  def getFourCorners(start: Long, size: Long)={
    val bleft = start+size-2
    (bleft*4)+(6*(size-1))
  }
}