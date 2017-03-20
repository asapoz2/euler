package euler

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.MutableList

object lexperm {
  def main(args: Array[String])={
//    var arr = ArrayBuffer(1,3,5,7)
//    println("rev sorted?"+(arr==arr.sortWith(_<_)))
//    swap(arr,1,2)
//    println("rev sorted?"+(arr==arr.sortWith(_<_)))
//    println("arr is "+arr.mkString(","))
//    var arr = ArrayBuffer(0,1,2,3)
//    println("arr is "+arr.mkString(","))
//    lexiter(arr)
//    println("arr is "+arr.mkString(","))
    
    var list = MutableList(0,1,2,3,4,5,6,7,8,9)
//    println("arr is "+list.mkString(","))
//    lexiter(list)
//    println("arr is "+list.mkString(","))
    
    for(i<-0 to 999998){
      list = lexiter(list)
    }
    println("arr is "+list.mkString(","))
    
  }
  
  def lexiter(seq: MutableList[Int])={
    val kz = for(i<-0 until seq.length-1 if seq(i)<seq(i+1)) yield i
    val k = kz(kz.length-1)
    val lz = for(i<-0 until seq.length if seq(k)<seq(i)) yield i
    val l = lz(lz.length-1)
    swap(seq,l,k)
    val (good, torev)=seq.splitAt(k+1)
    good ++ torev.reverse
    
  }
  
  def swap(seq: MutableList[Int], a: Int, b: Int)={
    val temp=seq(a)
    seq(a) = seq(b)
    seq(b) = temp
  }
  
  def isSorted(seq: ArrayBuffer[Int])={
    seq == seq.sortWith(_<_)
  }
  
  def revSorted(seq: ArrayBuffer[Int])={
    seq == seq.sortWith(_>_)
  }
  
}