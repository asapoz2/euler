package greeter
import math.abs

object fixedpointstuff {
  def main(args: Array[String]) {
    println("sqrt2="+sqrt(2))
  }
  val tolerance =0.001
  def isCloseEnough(x: Double, y: Double)=
    abs((x-y) / x)/x<tolerance
  def fixedPoint(f:Double=>Double)(firstGuess: Double) ={
    def iterate(guess: Double) :Double ={
      val next = f(guess)
      if(isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }
  def averageDamp(f: Double=>Double)(x:Double) =
    (x + f(x))/2
  def sqrt(x: Double) = fixedPoint(averageDamp(y=>x/y))(1)
  
}