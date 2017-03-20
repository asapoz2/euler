package euler

object powerfuldigcounts {
  def main(args: Array[String]): Unit = {
    val allofthem = (1 to 30).map(ofPower(_)).reduceLeft(_.++(_))
    println(allofthem)
    println(allofthem.length)
  }
  def ofPower(n: Int)={
    (BigInt(1) to 9).map((x: BigInt)=> x.pow(n)).filter(EulerUtil.intLength(_)==n)
  }
}