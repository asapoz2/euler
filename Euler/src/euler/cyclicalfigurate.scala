package euler

import scala.collection.mutable.MutableList
import scala.collection.mutable.HashSet
import scala.collection.mutable.HashMap

object cyclicalfigurate {
  val startMap = Array.ofDim[MutableList[Int]](100)
  val endMap = Array.ofDim[MutableList[Int]](100)
  val triSet = HashSet[Int]()
  val sqSet = HashSet[Int]()
  val pentSet = HashSet[Int]()
  val hexSet = HashSet[Int]()
  val heptSet = HashSet[Int]()
  val octSet = HashSet[Int]()
  val idxFunMap = HashMap[Int, (Int => Int)]()
  val idxSetMap = HashMap[Int, HashSet[Int]]()

  def setup() {
    idxFunMap.put(0, triAt)
    idxFunMap.put(1, sqAt)
    idxFunMap.put(2, pentAt)
    idxFunMap.put(3, hexAt)
    idxFunMap.put(4, heptAt)
    idxFunMap.put(5, octAt)
    idxSetMap.put(0, triSet)
    idxSetMap.put(1, sqSet)
    idxSetMap.put(2, pentSet)
    idxSetMap.put(3, hexSet)
    idxSetMap.put(4, heptSet)
    idxSetMap.put(5, octSet)
  }

  def main(args: Array[String]): Unit = {
    setup()
    (0 to 5).foreach((x: Int) => gotillfull(x,getIdx4(idxFunMap(x))))

    var cycle = List[Int]()
    var trilist = triSet.toList
    var idx = 0
    while (cycle.isEmpty) {
      val curr = trilist(idx)
      println(curr)
      cycle = seeifcycle(0, curr, (1 to 5), List[Int]())
      idx+=1
    }
    println(cycle)
    println(cycle.sum)
  }

  def seeifcycle(minidx: Int, newestval: Int, resttypes: IndexedSeq[Int], cycleSoFar: => List[Int]): List[Int] = {
    if (resttypes.isEmpty) {
      cycleSoFar
    }
    val fromStart = startMap(last2(newestval))
    if (fromStart == null) {
      List[Int]()
    } else {
      for (i <- 0 until fromStart.length) {
        val currElem = fromStart(i)
        val idxWithIt = resttypes.filter {
          idx: Int => idxSetMap(idx).contains(currElem)
        }.diff(List(minidx))
        val cycles = idxWithIt.map { x: Int =>
          seeifcycle(x, currElem, resttypes.diff(List(x)), cycleSoFar.++(List(currElem)))
        }.filter(!_.isEmpty)
        if (!cycles.isEmpty) {
          return cycles.head
        }
      }
      List[Int]()
    }
  }

  def last2(anInt: Int) = {
    anInt % 100
  }

  def first2(anInt: Int) = {
    anInt / 100
  }

  def gotillfull(fidx: Int, idxofarr: Int) = {
    var currIdx = idxofarr
    while (idxFunMap(fidx)(currIdx + 1) < 10000) {
      var newestval = idxFunMap(fidx)(currIdx)
      if (startMap(newestval / 100) == null) {
        startMap(newestval / 100) = MutableList[Int]()
      }
      startMap(newestval / 100) += newestval
      if (endMap(newestval % 100) == null) {
        endMap(newestval % 100) = MutableList[Int]()
      }
      endMap(newestval % 100) += newestval
      //update the set that has it
      idxSetMap(minidx).+=(newestval)
      currIdx+=1
    }
  }

  def getNextArr(state: Array[Int]) = {
    val minidx = (0 to 5).minBy((x: Int) => idxFunMap(x)(state(x)))
    val updated = state(minidx) + 1
    state.update(minidx, updated)
    val newestval = idxFunMap(minidx)(updated)

    if (startMap(newestval / 100) == null) {
      startMap(newestval / 100) = MutableList[Int]()
    }
    startMap(newestval / 100) += newestval
    if (endMap(newestval % 100) == null) {
      endMap(newestval % 100) = MutableList[Int]()
    }
    endMap(newestval % 100) += newestval
    //update the set that has it
    idxSetMap(minidx).+=(newestval)

    (minidx, newestval)
  }
  def getIdx4(f: (Int => Int)) = {
    var idx = 1
    while (f(idx) < 1000) {
      idx += 1
    }
    idx
  }
  def triAt(n: Int) = {
    n * (n + 1) / 2
  }
  def sqAt(n: Int) = {
    n * n
  }
  def pentAt(n: Int) = {
    n * (3 * n - 1) / 2
  }
  def hexAt(n: Int) = {
    n * (2 * n - 1)
  }
  def heptAt(n: Int) = {
    n * (5 * n - 3) / 2
  }
  def octAt(n: Int) = {
    n * (3 * n - 2)
  }
}