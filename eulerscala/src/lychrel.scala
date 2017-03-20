import euler.EulerUtil

import scala.collection.mutable.MutableList

/**
 * Created by alex on 10/24/2015.
 */
object lychrel {
  def main(args: Array[String]) {
    //println(isPal(943)); println(isPal(121)); println(isPal(13231); println(isPal(10201))
    //println(reverseDigs(943)); println(reverseDigs(121)); println(reverseDigs(13231)); println(reverseDigs(10201))
    //println(isLychrel(47));
    //println(isLychrel(10677))
    //println(isLychrel(0)); println(isLychrel(1))

//    val allLychrels = (1 until 10000).filter(isLychrel(_))
//    println(allLychrels)
//    println(allLychrels.length)

    //println(isLychrel(19))
    //println(reverseDigs(19))
    //println(isLychrel(9978))
  }

  def isLychrel(num: BigInt) = {
    def lychrelHelper(n: BigInt, ctr: Int): Boolean = {
      if (ctr >= 50) {
        true
      } else {
        val next = reverseDigs(n) + n
        if (isPal(next)) {
          false
        } else {
          lychrelHelper(next, ctr + 1)
        }
      }
    }
    lychrelHelper(num, 0)
  }

  def isPal(num: BigInt): Boolean = {
    reverseDigs(num).equals(num)
  }

  def reverseDigs(n: BigInt) = {
    BigInt(n.toString().reverse)
  }

  //  def isPal(num: BigInt): Boolean = {
  //    if (num < 10) {
  //      true
  //    } else {
  //      val ten = math.pow(10, EulerUtil.intLength(num) - 1).toLong
  //      val firstnum = num / ten
  //      if (num % 10 != firstnum) {
  //        false
  //      } else {
  //        var nextnum = (num - (firstnum * ten)) / 10
  //        if (nextnum % 10 == 0 && nextnum < ten / 10) {
  //          nextnum /= 10
  //        }
  //        isPal(nextnum)
  //      }
  //    }
  //  }

  //  def reverseDigs(n: BigInt)= {
  //    def reverseDigs(num: BigInt, numSoFar: => BigInt): BigInt = {
  //      if(num<=0){
  //        numSoFar
  //      }else {
  //        val ten = math.pow(10, EulerUtil.intLength(num) - 1).toLong
  //        reverseDigs(num / 10, numSoFar + ((num%10) * ten))
  //      }
  //    }
  //    reverseDigs(n,0)
  //  }

}













