import euler.EulerUtil

import scala.collection.mutable.MutableList

/**
 * Created by alex on 10/24/2015.
 */
object fivegonring {
  val allnums = (1 to 6)
  val tennums = (1 to 10)
  def main(args: Array[String]) {
    //println(combinationsWithLenUsing(3,(1 to 6)))
//    val str = getValidString(List(3,2,1))
//    println(str)
//    println(str.length)
//    println(getValidString(List(2,3,1)))
//    println(getValidString(List(6,5,1)))

//    val results = combinationsWithLenUsing(3,(1 to 6)).map(getValid3gon(_)).max
//    println(results)
    val results = combinationsWithLenUsing(5,tennums).map(getValid5gon(_)).max
    println(results)
  }

  def getValid5gon(pent: List[Int])={
    var left = tennums.diff(pent)
    val comp1 = pent(0) + pent(1)
    val comp2 = pent(2) + pent(1)
    val comp3 = pent(3) + pent(2)
    val comp4 = pent(3) + pent(4)
    val comp5 = pent(0) + pent(4)
    val lowest= left.head
    left = left.tail
    val total = lowest+comp1
    val goeswith2 = left.filter(_== total-comp2)
    left = left.diff(goeswith2)
    val goeswith3 = left.filter(_== total-comp3)
    left = left.diff(goeswith3)
    val goeswith4 = left.filter(_== total-comp4)
    left = left.diff(goeswith4)
    val goeswith5 = left.filter(_== total-comp5)
    left = left.diff(goeswith5)
    if(goeswith2.isEmpty || goeswith3.isEmpty|| goeswith4.isEmpty || goeswith5.isEmpty){
      ""
    }else{
      val builder = new StringBuilder
      builder.append(lowest).append(pent(0)).append(pent(1)).
        append(goeswith2.head).append(pent(1)).append(pent(2)).
        append(goeswith3.head).append(pent(2)).append(pent(3)).
        append(goeswith4.head).append(pent(3)).append(pent(4)).
        append(goeswith5.head).append(pent(4)).append(pent(0)).
        mkString
    }
  }

  def getValid3gon(tri: List[Int])={
    var left = allnums.diff(tri)
    val comp1 = tri(0) + tri(1)
    val comp2 = tri(2) + tri(1)
    val comp3 = tri(0) + tri(2)
    val lowest = left.head
    val total = lowest+comp1
    left = left.diff(List(lowest))
    if(total == left.head + comp2 && total == left.max + comp3){
      val builder = new StringBuilder
      builder.append(lowest).append(tri(0)).append(tri(1)).
      append(left.head).append(tri(1)).append(tri(2)).
      append(left.max).append(tri(2)).append(tri(0)).mkString
    }else if(total == left.max + comp2 && total == left.min + comp3){
      val builder = new StringBuilder
      builder.append(lowest).append(tri(0)).append(tri(1)).
        append(left.max).append(tri(2)).append(tri(0)).
        append(left.head).append(tri(1)).append(tri(2)).
        mkString
    }else{
      ""
    }
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
          list=MutableList.concat(list,
            appendAllCombosTill(endLen, start.++(rest.drop(i).take(1)), rest.diff(List(rest(i)))))
        }
        list
      }
    }
    appendAllCombosTill(endLen, List[Int](), using)
  }
}
