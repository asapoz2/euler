package greeter

object squares {
  def main(args: Array[String]) {
    //    var y = 1.0
    //    var x = 2.0
    //    var oldimprove = improve(x,y)
    //    while(y != oldimprove){
    //      oldimprove = y
    //      y = improve(x,oldimprove)
    //    }
    //    println(oldimprove)
    //    val s2 = sqrtIter(1,2)
    //    println(s2)
    //    println(sqrtIter(1,1e-6))
    //    println(sqrtIter(1,1e10))
    //    println(sqrtIter(1,0.1e-20)+"=?"+Math.sqrt(1e-20))
    //    println(sqrtIter(1,0.1e20)+"=?"+Math.sqrt(1e20))
//    println(factorial(1, 4))
//    println(sum(x=>x*x*x,1,3))
    println(combinetill((x: Int, y: Int)=>x*y,1)((x: Int)=>x)(1,4))
  }
  def improve(x: Double, y: Double) = (y + (x / y)) / 2
  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(x, guess), x)
  //def isGoodEnough(guess: Double, x: Double) = abs(guess * guess-x)<0.001
  def isGoodEnough(guess: Double, x: Double) = abs(guess * guess - x) < (x / 1000)
  def abs(aval: Double) = if (aval >= 0) aval else -aval
  def factorial(sofar: Int, n: Int): Int = if (n == 0) sofar else factorial(n * sofar, n - 1)
  def sum(f: Int => Int,a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a>=b) acc
      else loop(a+1, acc+f(a))
    }
    loop(a, 0)
  }
  def product(f: Int=> Int)(a: Int, b: Int): Int=
    if(a>b) 1 else f(a)*product(f)(a+1,b)
  def factorial(n: Int): Int=
    product((x: Int)=>x)(1, n)
  def combinetill(combine: (Int,Int)=>Int, unit: Int)(f: Int=>Int)(a: Int, b:Int): Int =
    if(a>b) unit
    else combine(f(a),combinetill(combine,unit)(f)(a+1,b))
  
  
  
}