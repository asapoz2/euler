package euler

import scala.collection.mutable.MutableList

object substrdivis {
  val threes = EulerUtil.combinationsWithLenUsing(3, (0 to 9))
  def main(args: Array[String]): Unit = {
    //println(getNextStartDig(13,289,EulerUtil.digitsOf(289)))

    //println(threes)
    //println(threes.filter(_%17==0))
    //println(threes.length)
    println(getCombosWithProperties(threes))
  }

  def getCombosWithProperties(threes: MutableList[Int]) = {
    val digssofar = new MutableList[BigInt]
    val candidates = threes.filter(_ % 17 == 0)
    val thirteens = getRemainingCandidates(candidates.map(BigInt(_)), 13, 1000,1)
    val elevens = getRemainingCandidates(thirteens,11,10000,10)
    val sevens = getRemainingCandidates(elevens,7,100000,100)
    val fives = getRemainingCandidates(sevens,5,1000000,1000)
    val three = getRemainingCandidates(fives,3,10000000,10000)
    val twos= getRemainingCandidates(three,2,100000000,100000)
    val finals=twos.map((x: BigInt)=>((BigInt(0) to BigInt(9)).filter(!EulerUtil.digitsOf(x).contains(_)).head*BigInt(1000000000))+x)
    finals.sum
  }
  def getRemainingCandidates(candidates: MutableList[BigInt], divisor: Int, multiplier: Int, candidateDiv: BigInt) = {
    var remaining = new MutableList[BigInt]
    for (i <- 0 until candidates.length) {
      val candidate = candidates.apply(i)
      val startDig = getNextStartDig(divisor, candidate/candidateDiv, EulerUtil.digitsOf(candidate))
      remaining = MutableList.concat(remaining, startDig.map(_ * multiplier + candidate))
    }
    remaining
  }

  def getNextStartDig(divisor: Int, currNum: BigInt, digsUsed: List[BigInt]) = {
    threes.filter((x: Int) =>
      (x % 100 == currNum / 10 && x % divisor == 0 && !digsUsed.contains(x / 100))).map(_ / 100)
  }
  def hasProperties(n: Int) = {
    val digs = EulerUtil.digitsOf(n)
    val slice = digs.slice(1, 4)
    val slicenum = EulerUtil.toDig(slice)
    slicenum % 2 == 0 &&
      EulerUtil.toDig(digs.slice(2, 5)) % 3 == 0 &&
      EulerUtil.toDig(digs.slice(3, 6)) % 5 == 0 &&
      EulerUtil.toDig(digs.slice(4, 7)) % 7 == 0 &&
      EulerUtil.toDig(digs.slice(5, 8)) % 11 == 0 &&
      EulerUtil.toDig(digs.slice(6, 9)) % 13 == 0 &&
      EulerUtil.toDig(digs.slice(7, 10)) % 17 == 0
  }
}