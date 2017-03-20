package euler

object pandigital {
  def main(args: Array[String]) = {
//        val pan1 = isPandigital(192)
//        val pan2 = isPandigital(193)
//        println("pan1 "+pan1+" pan2 "+pan2)
    val maxp = (1 to 10000).filter(isPandigital(_)).map { x:Int => pandigit(x).toInt }.max
    println(maxp)
  }
  def isPandigital(num: Int) = {
    var result = pandigit(num)
    if (result.length() != 9) {
      false
    } else {
      result.sorted.equals("123456789")
    }
  }
  def pandigit(num: Int) = {
    var result = num.toString()
    var mult = 2
    while (result.length() < 9) {
      result = result + (num * mult)
      mult += 1
    }
    result
  }
}