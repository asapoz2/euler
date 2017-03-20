package euler

object doublebasepalindromes {
  def main(args: Array[String])={
    val allpals = (1 to 1000000).par.filter(isDoublePal(_)).reduceLeft(_+_)
    println(allpals)
  }
  def isDoublePal(num: Int)={
    num.toString().equals(num.toString().reverse) && num.toBinaryString.equals(num.toBinaryString.reverse)
  }
}