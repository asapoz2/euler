package euler

object digitcancel {
  def main(args: Array[String]) = {
    val cancelled = for(num<-1 to 99; den<-1 to 99
      if isDigitCancelling(new Rational(num,den))&&new Rational(num,den).toFloat()<1) yield new Rational(num,den)
    println(cancelled)
    val simplified = cancelled.reduceLeft(_*_).simplified
    println(simplified)
  }
  def isDigitCancelling(rat: Rational) = {
    val numString = rat.numer.toString()
    val denString = rat.denom.toString()
    var common = numString.intersect(denString)
    common = common.replace("0", "")
    if (common.trim().isEmpty())
      false
    else {
      try{
        new Rational(numString.replaceFirst(common, "").toInt,
          denString.replaceFirst(common, "").toInt) == rat
      }catch{
        case e: Exception =>false
      }
    }
  }
  class Rational(x: Int, y: Int) {
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else (gcd(b, a % b))
    def simplified = {
      val g = gcd(x, y)
      new Rational(x / g, y / g)
    }
    val numer = x
    val denom = y
    override def toString() = this.numer + "/" + this.denom
    def exactEquals(other: Rational) = {
      (this.numer == other.numer) && (this.denom == other.denom)
    }
    def ==(other: Rational): Boolean = {
      val simplified = this.simplified
      val othersimp = other.simplified
      simplified.exactEquals(othersimp)
    }
    def *(other: Rational) = {
      new Rational(this.numer*other.numer,this.denom*other.denom)
    }
    def toFloat()={
      (numer.toFloat/denom)
    }
  }
}