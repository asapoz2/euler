package euler

object pandigitalprod {
  //val ends=(123456789 to 987654321).par.filter(isCandidate(_))
  def main(args: Array[String])={
//    println(isPandigitalProd(39,186,7254))
//    println(isPandigitalProd(39,186,7253))
    
    //val pandigitals = for(a<-1 to 9876543; b<-1 to 9876543 if isPandigitalProd(a,b,a*b)) yield (a.min(b),a.max(b))
//    val pandigitals = for(a<-1 to 999; b<-1 to 9999 if isPandigitalProd(a,b,a*b)) yield (a.min(b),a.max(b))
//    println(pandigitals.distinct)
//    val sumdist = pandigitals.distinct.map((f:(Int, Int)) => (f._1*f._2)).sum
//    println(sumdist)
    //println(ends.mkString(","))
    
    //val pandigitals = for(a<-1 to 999; b<-1 to 9999 if isPandigitalProd(a,b,a*b)) yield (a.min(b),a.max(b))
    val onedigitals = digitals(1,9,1000,9999)
    val twodigitals = digitals(10,99,100,999)
    val combo = onedigitals++twodigitals
    println(combo)
    val sum =combo.map((f:(Int, Int)) => (f._1*f._2)).distinct.sum
     println(sum)
  }
  def isCandidate(num: Int)={
    (num.toString().length()==num.toString().distinct.length())
  }
  def isCandidate(num: String)={
    (num.length()==num.distinct.length())
  }
  def isPandigitalProd(a: Int, b: Int, ans: Int)={
    (a.toString()+b.toString()+ans.toString()).sorted.equals("123456789")
  }
  def digitals(firstmin: Int, firstmax: Int, secondmin: Int, secondmax: Int)={
    for(a<-firstmin to firstmax; 
    b<-secondmin to secondmax if isPandigitalProd(a,b,a*b)) yield (a.min(b),a.max(b))
  }
  
  
  
}