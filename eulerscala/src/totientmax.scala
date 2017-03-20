import euler.EulerUtil

import scala.collection.mutable
import scala.collection.mutable.MutableList

/**
 * Created by alex on 11/2/2015.
 */
object totientmax {
  def main(args: Array[String]) {
    //val result = (2 to 1000000).maxBy(totient(_))
    //    val result = (2 to 10).maxBy(totient(_))
    //    val result = (500000 to 500630).map(totient(_))
    //    println("result is"+result)
//        println((2 to 10).map(totient(_)))
    //println(totient(1000000))
    //    println(getfactors(9))
    //    println(getfactors(21))
    //30030 13 5.213541666666667
    //510450 17 3.890625
    //570570 13x19  5.50318287037037
    //690690 13x23  5.450520833333333
    //870870 13x29  5.399739583333333
    //930930 13 x 31 5.387326388888889
    //903210 7,17,23 5.345703125
    //

    //152490 5, 17, 23
    //179010 5,17,27
    //205530 5,17,31


//    println(totient(390390))
//    println(totient(667590))
//    println(totient(788970))
    //    var currMax = 0.0
    //    var currIdx = 570560
    //    val currMult = 2
    //    while(currIdx<570572){
    //      val currVal = totient(currIdx)
    //      if(currVal>currMax){
    //        currMax = currVal
    //        println(getfactors(currIdx))
    //        println("idx: "+currIdx+" max: "+currMax)
    //      }
    //      currIdx+=1
    //    }
//    val primes = (2 to 23).filter(EulerUtil.isPrime(_))
//    val comb = combinationsWithLenUsing(6, primes)
//    println(comb)
//
//    var currMax = 0.0
//    var currIdx = 0
//    while (currIdx < comb.size) {
//      val closemil = tillmill(comb(currIdx))
//      //println(closemil)
//      val currVal = totient(closemil)
//      if (currVal > currMax) {
//        currMax = currVal
//        println(getfactors(currIdx))
//        println("closemil"+closemil+"idx: " + currIdx + " max: " + currMax)
//        //println("idx: " + currIdx + " max: " + currMax)
//      }
//      currIdx += 1
//    }
//    val primes = (2 to 50).filter(EulerUtil.isPrime(_))
//    println(primes)
//    println(tillmill(primes.toList))
  }

  def tillmill(list: List[Int])={
    var curr = 1
    var idx=0
    while(curr<1000000 && idx<list.length){
      curr*=list(idx)
      idx+=1
    }
    if(idx<list.length){
      curr/=list(idx-1)
    }
    curr
  }

  def totient(n: Int) = {
    //println(n)
    val factors = getfactors(n)
    val notprime = new mutable.HashSet[Int]
    factors.foreach((x: Int) => {
      var curr = x
      while (curr < n) {
        notprime.add(curr)
        curr += x
      }
    })

    n.toDouble / ((n - notprime.size) - 1)
  }

  def oldtot(n: Int) = {
    println(n)
    val result = ((2 until n).count(n % _ != 0) + 1)
    val ins = (2 until n).filter(n % _ != 0)
    n.toDouble / result
  }

  def getfactors(n: Int) = {
    var left = n
    var ctr = 2
    var result = new mutable.HashSet[Int]
    while (left > 1) {
      if (left % ctr == 0) {
        while (left % ctr == 0) {
          left /= ctr
        }
        result += ctr
      }
      ctr += 1
    }
    result
  }

  def combinationsWithLenUsing(endLen: Int, using: IndexedSeq[Int]) = {
    def appendAllCombosTill(endLen: Int, start: List[Int],
                            rest: IndexedSeq[Int]): MutableList[List[Int]] = {
      var list = new MutableList[List[Int]]
      if (start.length > endLen - 1) {
        list += start
        list
      } else {
        for (i <- 0 until rest.length) {
          list = MutableList.concat(list,
            appendAllCombosTill(endLen, start.++(rest.drop(i).take(1)), rest.diff(List(rest(i)))))
        }
        list
      }
    }
    appendAllCombosTill(endLen, List[Int](), using)
  }
}
