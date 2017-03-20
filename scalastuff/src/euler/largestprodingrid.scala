package euler

import java.nio.file.Paths
import java.nio.file.Files
import java.util.Scanner
import scala.runtime.ScalaRunTime._

object largestprodingrid {
  val grid=readGrid()
  def main(args: Array[String]): Unit = {
    println(stringOf(grid))
    println(maxHorizontal())
    println(maxVertical())
    println(maxLD())
    println(maxRU())
  }
  
  def getentry(row: Int, col: Int)={
    if(row<0||row>19||col<0||col>19){
      0
    }else{
      grid(row)(col)
    }
  }
  
  def maxLD()={
    (0 to 16).map((x:Int)=>maxOfLDLine(x)).max
  }
  
  def maxRU()={
    (0 to 16).map((x:Int)=>maxOfRULine(x)).max
  }
  
  def maxOfRULine(col: Int) = {
    def sumFour(start: Int)=getentry(start,col)*getentry(start+1,col-1)*getentry(start+2,col-2)*
    getentry(start+3,col-3)
    (3 to 19).map(sumFour(_)).max
  }
  
  def maxOfLDLine(col: Int) = {
    def sumFour(start: Int)=getentry(start,col)*getentry(start+1,col+1)*getentry(start+2,col+2)*
    getentry(start+3,col+3)
    (0 to 16).map(sumFour(_)).max
  }
  
  def maxVertical()={
    (0 to 19).map((x:Int)=>maxOfVertLine(x)).max
  }
  def maxOfVertLine(col: Int) = {
    def sumFour(start: Int)=grid(start)(col)*grid(start+1)(col)*grid(start+2)(col)*grid(start+3)(col)
    (0 to 16).map(sumFour(_)).max
  }
  
  def maxHorizontal()={
    (0 to 19).map((x:Int)=>maxOfHorLine(grid(x))).max
  }
  
  def maxOfHorLine(arr: Array[Int]) = {
    def sumFour(start: Int)=arr(start)*arr(start+1)*arr(start+2)*arr(start+3)
    (0 to 16).map(sumFour(_)).max
  }
  
  def readGrid()={
    val gridfile = Paths.get("res/grid")
    val reader = Files.newBufferedReader(gridfile);
    val grid = Array.ofDim[Int](20,20)
    var currentLine:String=null;
    var idx=0
    var line=0
    while(line<20){
      currentLine=reader.readLine();
      val scanner = new Scanner(currentLine)
      while(scanner.hasNextInt()){
        grid(line)(idx)=scanner.nextInt();
        idx+=1
      }
      idx=0
      line+=1
    }
    grid
  }
}