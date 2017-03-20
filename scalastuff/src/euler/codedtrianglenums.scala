package euler

import scala.collection.mutable.HashSet
import java.nio.file.Paths
import java.nio.file.Files
import java.util.regex.Pattern

object codedtrianglenums {
  val theset = triangleSet()
  def main(args: Array[String]): Unit = {
//    println(theset)
//    println(isTriWord("SKY"))
//    println(isTriWord("SKZ"))
//    println(readFile())
    val file = readFile()
    val pattern = Pattern.compile("\"([^\"]*)\"")
    val matcher = pattern.matcher(file)
    var sum=0
    while(matcher.find()){
      val word = matcher.group(1)
      sum+=(if(isTriWord(word)) 1 else 0)
    }
    println(sum)
  }
  def isTriWord(str: String)={
    theset.contains(valOfWord(str))
  }
  def valOfWord(str: String)={
    (0 until str.length()).map(str.charAt(_)-'A'+1).sum
  }
  def triangleSet()={
    val theset = new HashSet[Int]
    var inc=2
    var theval=1
    while(theval<(26*20)){
      theset+=theval
      theval+=inc
      inc+=1
    }
    theset
  }
  def readFile()={
    val gridfile = Paths.get("res/words.txt")
    val reader = Files.newBufferedReader(gridfile);
    reader.readLine();
  }
}