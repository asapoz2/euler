package euler

object selfpowers {
  def main(args: Array[String]): Unit = {
    //println(Long.MaxValue)
    println( (1L to 1000L).map(last10digs(_)).sum%10000000000L )
  }
  def last10digs(self: Long)={
    var ctr=self
    var result=self
    while(ctr>1){
      result*=self
      result=result%10000000000L
      ctr-=1
    }
    result
  }
}