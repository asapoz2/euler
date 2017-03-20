package euler

import scala.collection.mutable.MutableList

object recipricalcycles {
  def main(args: Array[String]): Unit = {
    //val ten = (1 to 1000).map(new Rational(1,_)).map(findCycle(_)).maxBy(_.length)
    val ten = (1 to 1000).map(new Rational(1,_)).maxBy(findCycle(_).length)
    println(ten)
    //println(findCycle(first).map(_.nextDec()))
  }
  def findCycle(rat: Rational)={
    val seen = new MutableList[Rational]
    seen+=rat
    var nextRat = rat
    while(nextRat.numer!=0 && !seen.contains(nextRat.nextRemainder())){
      nextRat=nextRat.nextRemainder()
      seen+=nextRat
    }
    if(nextRat.numer!=0){
      new MutableList[Rational]
    }
    seen.dropWhile(_!=nextRat.nextRemainder())
  }
  class Rational(x: Int, y: Int) {
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else (gcd(b, a % b))
    private val g = gcd(x, y)
    val numer = x / g
    val denom = y / g
    def nextRemainder() = {
      val rem = numer % denom
      if (rem == 0) {
        new Rational(0, 1)
      } else
        new Rational((numer * 10) % denom, denom)
    }
    def nextDec() = {
      (numer * 10) / denom 
    }
    override def equals(o: Any)={
      if(o.getClass()!=this.getClass){
        false
      }
      val other:Rational = o.asInstanceOf[Rational]
      (other.numer == this.numer) && (other.denom == this.denom)
    }

    override def toString() = this.numer + "/" + this.denom
  }
}