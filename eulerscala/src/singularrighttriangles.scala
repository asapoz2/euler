import scala.collection.mutable

/**
 * Created by alex on 10/26/2015.
 */
object singularrighttriangles {
  var maxSquareIdx = 1
  val squaresSoFar = mutable.MutableList[Long]()
  val perimMap = mutable.HashMap[Double,(Int, Int)]()

  def main(args: Array[String]) {
    //    println(oneTriFromP(12)); println(oneTriFromP(13)); println(oneTriFromP(48))
    //val results = (11 to 50).filter(oneTriFromP(_))
    //println(results)
    //println(oneTriFromP(120))
    //    val results = (11 to 1500000).par.count(oneTriFromP(_))
    //    println(results)

    //    val result = for (a <- 1 to 20; b <- 1 to 20;
    //                      if (intrt(a, b))) yield (a + b + squaresSoFar(a * a + b * b))
    //    println(result)
    squaresSoFar += 0
    for (a <- 3 to 750000; b <- 3 to 750000;
        c = findSquareRoot(a * a + b * b); if c>0) {
      val perim = (a + b + c)
      val newval = (a.min(b), a.max(b))
      println("a:" + a + "b:" + b)
      if (!perimMap.contains(perim)) {
        perimMap(perim) = newval
      } else if (perimMap(perim) != newval) {
        perimMap(perim) = (-1, -1)
      }
    }
    val result = perimMap.filter(_._1 < 150000).values.count(_._1 > 0)
    println(result)
    println(perimMap)

  }

  def intrt(a: Long, b: Long) = {
    //isSquare((a * a + b * b))
    math.sqrt((a * a + b * b)).isWhole()
  }

  def oneTriFromP(p: Long) = {
    println(p)
    //println((1L until p/2).filter(isOKB(_,p)))
    //val size = (1L until p/2).count(isOKB(_,p))
    val size = (math.sqrt(p.toDouble).round until p / 2).par.count(isOKB(_, p))
    size == 1 || size == 2
  }

  def isOKB(b: Long, p: Long) = {
    //((p*p)-(2*b*p)+(2*b*b)) % (2*p-2*b) == 0
    (p * p) % (2 * p - 2 * b) == 0
  }

  def findSquareRoot(n: Long) = {
    if (n > maxSquareIdx * maxSquareIdx) {
      calcSquaresTill(n)
    }
    val idx = math.sqrt(n).toInt
    if(squaresSoFar(idx - 1) == n){
      squaresSoFar(idx - 1)
    }else if(squaresSoFar(idx) == n){
      squaresSoFar(idx)
//    }else if(squaresSoFar(idx + 1) == n) {
//      squaresSoFar(idx + 1)
//    }else if(squaresSoFar(idx + 2) == n){
//      squaresSoFar(idx + 2)
    }else{
      -1L
    }
  }

  def calcSquaresTill(n: Long) {
    var nextSquare = 0
    while (nextSquare < n) {
      nextSquare = (maxSquareIdx + 1) * (maxSquareIdx + 1)
      maxSquareIdx += 1
      squaresSoFar.update(nextSquare, maxSquareIdx)
    }
  }
}
