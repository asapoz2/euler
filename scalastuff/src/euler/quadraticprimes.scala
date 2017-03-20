package euler

object quadraticprimes {
  def main(args: Array[String]) = {
//    def forty = makeForm(1, 41)
//    val consec = getConsecutivePrimes(forty)
//    println(consec)
//        def seventy9=makeForm(-79,1601)
//        val consec79 = getConsecutivePrimes(seventy9)
//        println(consec79)
//    val posprimesto100 = (1 to 1000).filter(isPrime _)
//    val primesto1000 = IndexedSeq.concat(
//      (for (i <- 0 until posprimesto100.length) yield -posprimesto100(i)), posprimesto100)
//    println(primesto1000)
//    var max=0
//    var maxa=0
//    var maxb=0
//    for(a<-0 until primesto1000.length; b<-0 until primesto1000.length){
//      val numprimes = getConsecutivePrimes(makeForm(a,b))
//      if(numprimes>max){
//        println("found new max is "+numprimes+" a is "+a+" b is "+b)
//        max = numprimes
//        maxa = a
//        maxb = b
//      }
//    }
//    println("max is "+max+" a is "+maxa+" b is "+maxb)
        
    val seq1000 = (-1000 to 1000)
    var max=0
    var maxa=0
    var maxb=0
    for(a<-0 until seq1000.length; b<-0 until seq1000.length){
      val numprimes = getConsecutivePrimes(makeForm(seq1000(a),seq1000(b)))
      if(numprimes>max){
        println("found new max is "+numprimes+" a is "+seq1000(a)+" b is "+seq1000(b))
        max = numprimes
        maxa = seq1000(a)
        maxb = seq1000(b)
      }
    }
    println("max is "+max+" a is "+maxa+" b is "+maxb)
  }
  def isPrime(n: Int) = (n>0)&& (!((2 until n - 1) exists (n % _ == 0)))
  def getConsecutivePrimes(f: Int => Int) = {
    var i = 0
    while (isPrime(f(i))) {
      i += 1
    }
    i
  }
  def makeForm(a: Int, b: Int) = {
    (n: Int) => math.pow(n, 2).toInt + a * n + b
  }
}