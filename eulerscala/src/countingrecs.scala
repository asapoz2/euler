import scala.collection.mutable

/**
 * Created by alex on 10/26/2015.
 */
object countingrecs {
  //val map = Array.ofDim[Int](1000,1000)
  def main(args: Array[String]) {
    //println(howMany(2, 1, 3, 2))
//    println(allRecs(3,2))
//    println(allRecs(5,5))
//    println(allRecs(10,10))

    //53x53 = 2047761
//    var x = 2
//    var xrecs = 9
//    while(xrecs<2000000){
//      x+=1
//      xrecs=allRecs(x,x)
//    }
//    println(x); println(xrecs)
//    val result = (1 to 63).map(findClosestTo(_)).minBy(_._3)
//    println(result._1 * result._2)

  }

  def intrt(a: Int, b: Int)={
    math.sqrt((a*a+b*b).toDouble).isWhole()
  }

  def findClosestTo(w: Int)={
    var x = 1
    var lastrecs=0
    var xrecs = 0
    while(xrecs<2000000){
      x+=1
      lastrecs=xrecs
      xrecs=allRecs(w,x)
    }
    if(2000000-lastrecs<xrecs-2000000){
      (w,x-1,2000000-lastrecs)
    }else{
      (w,x, xrecs-2000000)
    }
  }

  def allRecs(recW: Int, recH: Int)={
    (for(w<-1 to recW; h<-1 to recH) yield howMany(w,h,recW,recH)).sum
  }

  def howMany(recW: Int, recH: Int, w: Int, h: Int) = {
    (w-recW+1)*(h-recH+1)
  }

}
