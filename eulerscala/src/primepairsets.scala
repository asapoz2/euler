import euler.EulerUtil

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.MutableList
import scala.collection.mutable.HashMap

object primepairsets {
  val primesSoFar = new ArrayBuffer[Long]
  val concatMap = new HashMap[Long, Set[Long]]

  def main(args: Array[String]): Unit = {
    //println(concatLongs(7,109) + " "+concatLongs(109,7))
    //println(concatsprime(7,109)+" "+concatsprime(6,103))

    primesSoFar += 3
    concatMap.put(3,Set[Long]())
    var currPrime = 3L
    var len = 1
    var continue = true
    var lastcat = (0L to 1L).toList
    while (continue) {
      currPrime = EulerUtil.calcNextPrime(currPrime, primesSoFar)
      len += 1
      lastcat = evalCurrPrime(currPrime, len, 4)
      println(lastcat)
      continue = lastcat.length < 2
    }
    println(lastcat)
    println(lastcat.sum)
  }

  def evalCurrPrime(currPrime: Long, len: Int, aim: Int) = {
    println(currPrime)
    val concatWithThis = for (
      primeIdx <- 0 until primesSoFar.length - 1 if (concatsprime(primesSoFar(primeIdx), currPrime))
    ) yield primesSoFar(primeIdx)
    concatMap.update(currPrime, concatWithThis.toSet)

    if (concatWithThis.length < (aim - 1)) {
      List[Long]()
    } else {
      //fourChain(concatWithThis.toList).++(List(currPrime))
      //goThroughOneChain(concatWithThis.toList).++(List(currPrime))
      pairsFromList(concatWithThis.toList).++(List(currPrime))
    }
  }

  def pairsFromList(list: List[Long]): List[Long] = {
    var ret = List[Long]()
    val rev = list.reverse
    var i = 0
    var continue = true
    while (continue && i < list.length) {
      val ith = rev(i)
      val firstMatches = concatMap(ith)
      val firstinter = rev.filter(firstMatches.contains(_))
      var j = 0
      while (continue && j < firstinter.length) {
        val jth = firstinter(j)
        val secondMatches = concatMap(jth)
        val secondinter = firstinter.filter(secondMatches.contains(_))
        var k = 0
        while (continue && k < secondinter.length) {
          val kth = secondinter(k)
          val thirdMatches = concatMap(kth)
          val thirdinter = secondinter.filter(thirdMatches.contains(_))
          var l = 0
          while (continue && l < thirdinter.length) {
            val kth = thirdinter(l)
            ret = List(ith, jth, kth)
            continue = false
          }
          k += 1
        }
        j += 1
      }
      i += 1
    }
    ret
  }


def fourChain(list: List[Long]): List[Long] = {
  var i = 0
  var ret = List[Long]()
  var continue = true
  val toset = list.toSet
  while (continue && i < list.length) {
    val ith = list(i)
    val iset = concatMap.getOrElse(ith, Set[Long]())
    var firstinter = iset.intersect(toset)
    var j = 0
    while (j < firstinter.size && continue) {
      val jth = firstinter.drop(j).head
      val jset = concatMap.getOrElse(jth, Set[Long]())
      var secondinter = jset.intersect(toset)
      var k = 0
      while (k < secondinter.size && continue) {
        val kth = secondinter.drop(k).head
        if (iset.contains(kth)) {
          ret = List(ith, jth, kth)
          continue = false
        }
        k += 1
      }
      j += 1
    }
    i += 1
  }
  ret
}

def goThroughOneChain(list: List[Long]): List[Long] = {
  var i = 0
  var ret = List[Long]()
  var continue = true
  val toset = list.toSet
  while (continue && i < list.length) {
    val ith = list(i)
    val iset = concatMap.getOrElse(ith, Set[Long]())
    var firstinter = iset.intersect(toset)
    var j = 0
    while (j < firstinter.size && continue) {
      val jth = firstinter.drop(j).head
      val jset = concatMap.getOrElse(jth, Set[Long]())
      var secondinter = jset.intersect(firstinter)
      var k = 0
      while (k < secondinter.size && continue) {
        val kth = secondinter.drop(k).head
        val kset = concatMap.getOrElse(kth, Set[Long]())
        var thirdinter = kset.intersect(secondinter)
        var l = 0
        while (l < thirdinter.size && continue) {
          val lth = thirdinter.drop(l).head
          //            if (iset.contains(kth) && iset.contains(lth)
          //                && jset.contains(lth)) {
          if (allConcat(ith, jth, kth, lth)) {
            ret = List(ith, jth, kth, lth)
            continue = false
          }
          l += 1
        }
        k += 1
      }
      j += 1
    }
    i += 1
  }
  ret
}

def allConcat(ith: Long, jth: Long, kth: Long, lth: Long) = {
  concatsprime(ith, jth) && concatsprime(kth, lth) && concatsprime(ith, lth) &&
    concatsprime(jth, lth) && concatsprime(ith, kth) && concatsprime(jth, kth)
}

def goTogether(list: List[Long], aim: Int) = {
  var result = MutableList[List[Long]]()
  for (
    i <- 0 until list.length - 3; j <- i until list.length - 2; k <- j until list.length - 1;
    l <- k until list.length
  ) {
    //val listi = concatMap(list(i))
    val listj = concatMap.getOrElse(list(j), Set[Long]())
    val listk = concatMap.getOrElse(list(k), Set[Long]())
    val listl = concatMap.getOrElse(list(l), Set[Long]())
    val valk = list(k)
    val vali = list(i)
    val valj = list(j)
    val vall = list(l)
    if (listk.contains(valj) && listk.contains(vali) &&
      listj.contains(vali) &&
      listl.contains(vali) && listl.contains(valj) && listl.contains(valk)) {
      result += List(vali, valk, valj, vall)
    }
  }
  if (result.isEmpty) {
    List[Long]()
  } else {
    result.head
  }
}
def largestSubset(list: List[Long]): List[Long] = {
  if (list.length <= 1) {
    list
  } else {
    (0 until list.length - 1).map(largestSubset(list, _)).maxBy(_.length)
  }
}
def largestSubset(list: List[Long], headIdx: Int): List[Long] = {
  if (list.length <= 1) {
    list
  }
  val head = list.drop(headIdx).head
  val concatWithHead = list.take(headIdx).++(list.drop(headIdx + 1)).filter(concatMap.getOrElse(_, Set[Long]()).contains(head))
  val theirSubset = largestSubset(concatWithHead)
  theirSubset.++(list.drop(headIdx).take(1))
}
def concatsprime(num1: Long, num2: Long) = {
  EulerUtil.isPrimeNoEvens(concatLongs(num1, num2)) && EulerUtil.isPrimeNoEvens(concatLongs(num2, num1))
}
def concatLongs(num1: Long, num2: Long) = {
  num1 * (math.pow(10, EulerUtil.intLength(num2)).toLong) + num2
}
}