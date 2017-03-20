import scala.collection.mutable

/**
 * Created by alex on 10/28/2015.
 */
object singularright2 {
  //1,500,000
  // 750,000
  //866.02540378443864676372317075294
  var maxSquareIdx = 1
  val squaresSoFar = mutable.HashSet[Long]()
  val perimMap = mutable.HashMap[Long, Long]()

  def main(args: Array[String]) {
    setup()
    //calcSquaresTill(400)
    val limit = 1500000L*1500000L
    //calcSquaresTill(limit)
    //println(squaresSoFar)
    //    println(isOKC(5*5))
    //    println(isOKC(6*6))
    //println((1 to 867).map(gettrifromc(_)).filter(_.size>0))
    val forval = for (i <- 3L until 1500000) {
      val perim = gettrifromc(i*i)
      for (j <- 0 until perim.length) {
        perimMap.update(perim(j), perimMap.getOrElse(perim(j), 0L) + 1)
      }
    }
    println(forval)
    println(perimMap.maxBy(_._1))
    val cnt = perimMap.filter { x: (Long, Long) => x._2 == 1 && x._1 < 1500000 }
    println(cnt)
    println(cnt.size)
  }

  def gettrifromc(cSq: Long) = {
    println(cSq)
    //find if 2 squares add to this one
    var currIdx = 0
    var currVal = 0
    val c = math.sqrt(cSq).toLong
    val ret = new mutable.MutableList[Long]
    val dist = for (a <- 2L to c; currVal = a * a;
                    if math.sqrt(cSq - currVal).isWhole()) yield
    (c + math.sqrt(cSq - currVal).toInt + math.sqrt(currVal).toInt)
    dist.distinct
  }

  def isOKC(c: Int) = {
    //find if 2 squares add to this one
    var currIdx = 0
    var currVal = 0
    var ret = false
    while (currVal < c && !ret) {
      currIdx += 1
      currVal = currIdx * currIdx
      ret = squaresSoFar.contains(c - currVal)
    }
    ret
  }


  def setup(): Unit = {
    squaresSoFar += 1
  }

  def calcSquaresTill(n: Long) = {
    var nextSquare = maxSquareIdx * maxSquareIdx
    while (nextSquare < n) {
      nextSquare = (maxSquareIdx + 1) * (maxSquareIdx + 1)
      maxSquareIdx += 1
      squaresSoFar += nextSquare
    }
    nextSquare
  }
}
