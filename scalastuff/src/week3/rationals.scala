package week3



object rationals {
  def main(args: Array[String]) {
    var aRat = new Rational(1,2)
    println(aRat)
    var bRat = new Rational(1,3)
    println(aRat.sub(bRat))
    val x = new Rational(1,3)
    val y = new Rational(5,7)
    var z = new Rational(3,2)
    println(x.sub(y).sub(z))
    println(y.add(y))
    
  }
  
}
class Rational(x: Int, y: Int){
  private def gcd(a: Int, b:Int) :Int= if(b==0) a else (gcd(b,a%b))
  private val g = gcd(x,y)
  val numer = x / g
  val denom = y / g
  def add(that: Rational) = new Rational(numer*that.denom + that.numer * denom, denom*that.denom)
  def sub(that: Rational) = new Rational(
      numer*that.denom - that.numer*denom,denom*that.denom)
  def neg = new Rational(-numer,denom)
  
  override def toString() = this.numer +"/"+this.denom
}