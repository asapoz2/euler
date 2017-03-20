package euler

import scala.collection.mutable.ArrayBuffer

object goldbachsother {
  val primesSoFar=new ArrayBuffer[Int]
  val squaresSoFar = new ArrayBuffer[BigInt]
  var lastSquareIdx =1
  def main(args: Array[String]): Unit = {
//    val tests = (9 to 1000).filter(isOddComposite(_))
//    val filtered = tests.filter(canBeGold(_))
//    println(tests)
//    println(filtered)
    squaresSoFar+=1
    
    primesSoFar+=2
    primesSoFar+=3
    var currComp: Int=3
    var continue = true
    while(continue){
      currComp = calcNextComp(currComp)
      //addSquaresUnder(currComp)
      continue = canBeGoldWhile(currComp) && currComp<1000000
    }
    println(currComp)
  }
  
  def addSquaresUnder(n: BigInt){
    var currSquare = squaresSoFar.last
    while(currSquare < n) {
      lastSquareIdx+=1
      currSquare=lastSquareIdx*lastSquareIdx
      squaresSoFar+=currSquare
    }
  }
  
  def calcNextComp(n: Int)={
    var curr = n+2
    while(EulerUtil.isPrime(curr)){
      primesSoFar+=curr
      curr+=2
    }
    curr
  }
  def canBeGoldWhile(n: Int)={
    var hasntfound=true
    var primeIdx=0
    val primeLen = primesSoFar.length
    while(hasntfound && primeIdx<primeLen){
      //hasntfound = isThereSquareWhile(n,primeIdx)
      val target = (n-primesSoFar(primeIdx))/2
      hasntfound= !math.sqrt(target).isWhole()
      primeIdx+=1
    }
    !hasntfound
  }
  def isThereSquareWhile(n: BigInt, primeIdx: Int)={
    var squareIdx = 0
    var hasntfound=true
    val squareLen = squaresSoFar.length
    val target = (n-primesSoFar(primeIdx))/2
    while(hasntfound && squareIdx<squareLen){
      hasntfound = (target != squaresSoFar(squareIdx))
      squareIdx+=1
    }
    !hasntfound
  }
  
  def canBeGoldOpt(n: BigInt)={
    //!primesSoFar.filter((x:BigInt)=>isThereSquare((n-x)/2,squaresSoFar)).isEmpty
    true
  }
  def canBeGold(n: BigInt)={
    val primes = (BigInt(1) until n).filter(EulerUtil.isPrime(_))
    //(n-prime)/2
    val squares = (BigInt(1) until n).map(_*2)
    !primes.filter((x:BigInt)=>isThereSquare((n-x)/2,squares)).isEmpty
  }
  def isThereSquare(target: BigInt, squares: IndexedSeq[BigInt])={
    squares.filter(target==_).isEmpty
  }
  def isOddComposite(n: BigInt)={
    val byteval = n.byteValue()
    !EulerUtil.isPrime(n) && (byteval&1)==1
  }
}