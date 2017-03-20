package euler

import java.util.Scanner
import java.nio.file.Paths
import java.nio.file.Files
import scala.runtime.ScalaRunTime._

object largestprodinseries {
  val series = Array.ofDim[BigInt](1000)
  def main(args: Array[String]): Unit = {
    readGrid()
    //println(stringOf(series))
    val maxstart=(0 to 987).maxBy(prod13(_))
    val maxseries=(maxstart until 13+maxstart).map(series(_))
    println(maxseries)
    println(prod13(maxstart))
  }
  
  def prod13(start: Int)={
    val thirt = (0 until 13).map((x:Int)=>series(start+x))
    if(thirt.exists(_==0)){
      BigInt(0)
    }else{
      thirt.reduceLeft(_*_)
    }
  }
  
  def readGrid()={
    val gridfile = Paths.get("res/dig1000")
    val reader = Files.newBufferedReader(gridfile);
    var currentLine:String=null;
    var arridx=0
    var line=0
    while(line<20){
      currentLine=reader.readLine();
      for(i<-0 until currentLine.length()){
        series(arridx+i)=currentLine.charAt(i)-'0'
      }
      arridx+=currentLine.length()
      line+=1
    }
  }
}