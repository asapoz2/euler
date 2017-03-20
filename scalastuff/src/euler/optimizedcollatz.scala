package euler

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.MutableList

object optimizedcollatz {
  val memos= Array.ofDim[BigInt](1000000)
  def main(args: Array[String]): Unit = {
    //val hundred =  (2 to 100).map(howLongTillOne(_,0,new MutableList[BigInt]))
    val hundred =  (500000 to 500100).par.map(howLongTillOne(_,0,new MutableList[BigInt]))
    println(hundred)
    println(5000000+hundred.indexOf(hundred.max))
  }
  def howLongTillOne(n: BigInt, addAtEnd:BigInt, toSetAtEnd: MutableList[BigInt]): BigInt={
    var memo: BigInt=0
    if(n<1000000 && (memo= memos.apply(n.toInt))!=null && memo!=null){
      setAtEnd(memo, toSetAtEnd)
      memo + addAtEnd
    }
    val nextCol = nextCollatz(n)
    if(nextCol==1){
      setAtEnd(2,toSetAtEnd.reverse)
      2+addAtEnd
    }else{
      toSetAtEnd+=n
      howLongTillOne(nextCol,1+addAtEnd,toSetAtEnd)
    }
  }
  def setAtEnd(startVal: BigInt, toSetAtEnd: MutableList[BigInt])={
    var currVal=startVal
    for(i<-0 until toSetAtEnd.length){
      val toSet = toSetAtEnd(i)
      if(toSet<1000000 && memos.apply(toSet.toInt)==null){
        memos.update(toSet.toInt, currVal)
      }
      currVal+=1
    }
  }
  def nextCollatz(n: BigInt)={
    if(n%2==0){
      n/2
    }else{
      3*n+1
    }
  }
}