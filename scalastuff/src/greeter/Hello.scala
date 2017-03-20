package greeter
object Hello {
  def main(args: Array[String]) {
    var vals = (1 to 5).map(_*2)
    vals.map(println(_))
    println(abs(-4))
    println("Hello, World!")
    println(or(false,true))
    println(or(true,true))
    println(or(true,false))
    println(or(false,false))
    
  }
  def abs(x: Int) = if(x>=0) x else -x
  def and(x: Boolean, y:Boolean) = {
    if(x) y else false
  }
  def or(x:Boolean, y: => Boolean) = if(x) true else y
  
}