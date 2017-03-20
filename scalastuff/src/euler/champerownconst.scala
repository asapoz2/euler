package euler

object champerownconst {
  def main(args: Array[String]): Unit = {
//    println(lengthofset(10))
    //val mapped = (1 to 1000).map(nthdig(_))
//    println(mapped.mkString(","));
   
//    println(nthdig(10))

        val fin = 1*nthdig(11)*nthdig(101)*nthdig(999+2)*nthdig(9999+2)*nthdig(99999+2)*nthdig(999999+2)
    println(fin)
  }
  def nthdig(n: Int)={
    var currn=n
    var i =0
    var len=0
    while(len<n){
      len+=lengthofset(i)
      i+=10
    }
    //found bigger, dec
    len-=lengthofset(i-10)
    //account for zero
    currn-=len
    i-=10
    nthinset(currn-1,i)
  }
  def lengthofset(start: Int)={
    10*intLength(start)
  }
  def intLength(n: Int)={
    if(n==0){
      1
    }else{
      (Math.log10(n)+1).toInt
    }
  }
  def nthinset(n: Int, start: Int)={
    var curr=""
    val curr10=start
    for(i<-curr10 to (9+curr10)){
      curr=curr+i
    }
    //print((curr.charAt(n)-'0')+" ")
    curr.charAt(n)-'0'
  }
}