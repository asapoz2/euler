import euler.EulerUtil

import scala.collection.mutable
import scala.collection.mutable.MutableList

/**
 * Created by alex on 10/24/2015.
 */
object cubicpermutations {
  val cubeList = mutable.MutableList[Long]()
  val emptyList = MutableList[Long]()
  def main(args: Array[String]) {
      //println(isPermOf(66430125,41063625)); println(isPermOf(66430125,41063623))
    var cubeIdx = 1
    var continue = true
    var perms = emptyList
    while(continue){
      val cube = nextCube(cubeIdx)
      println(cube)
      perms = findPerms(cube)
      cubeIdx+=1
      continue = perms.length<5
    }
    println(perms)
  }

  def findPerms(currCube: Long)={
    cubeList.filter(isPermOf(currCube,_))
  }

  def nextCube(cubeIdx: Long)={
    val cube = cubeIdx*cubeIdx*cubeIdx
    cubeList+=cube
    cube
  }

  def isPermOf(n: Long, n2: Long)={
    if(EulerUtil.intLength(n)!=EulerUtil.intLength(n2)){
      false
    }else {
      digsOf(n).sorted.equals(digsOf(n2).sorted)
    }
  }
  def digsOf(n: Long) = {
    var curr = n
    val digs = new MutableList[Int]
    while (curr > 0) {
      digs.+=((curr % 10).toInt)
      curr /= 10
    }
    digs.toList
  }
}
