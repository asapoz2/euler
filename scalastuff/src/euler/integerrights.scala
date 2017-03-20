package euler

object integerrights {
  def main(args: Array[String])={
//    val b = findAdjWithP(4,12)
//    print(b)
//    val solutions = numSolutions(120)
//    println("num solutions ="+solutions)
    var max = 0
    var maxp = 0
    for(a<-10 to 1000){
      var sols = numSolutions(a)
      if(sols>max){
        println("new max "+sols+" at "+a)
        max = sols
        maxp = a
      }
    }
    println("max solutions is "+max+" with p:"+maxp)
  }
  def findPerim(a: Int, b: Int)={
    a + b + math.sqrt(math.pow(a, 2)+math.pow(b,2))
  }
  def findAdjWithP(side: Int, p: Int)={
    (math.pow(p,2)-(2*side*p))/(2*p-2*side)
  }
  def numSolutions(p: Int)={
    val solutions = for(a<-1 to p; b=findAdjWithP(a,p) 
      if b.isWhole() && b>0) yield ((a.max(b.toInt)),a.min(b.toInt))
    solutions.distinct.length
  }
}