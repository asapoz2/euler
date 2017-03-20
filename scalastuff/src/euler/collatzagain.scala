package euler

object collatzagain {
  def main(args: Array[String]): Unit = {
    println((500000 to 999999).maxBy(nextCollatz(_,0)))
     //println((500000 to 500100).maxBy(nextCollatz(_,0)))
  }
  def nextCollatz(n: Long, end: => Long): Long={
    //println(n)
    if(n==1){
      1+end
    }else{
      var inc=0
      val next= if(n.&(1) == 1){
        inc=1
        3*n+1 
        }else{
        var curr=n
        while(curr.&(1)==0){
          curr.>>=(1)
          inc+=1
        }
        curr
      }
      nextCollatz(next,end+inc)
    }
  }
}