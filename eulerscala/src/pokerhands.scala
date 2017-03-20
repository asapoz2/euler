import scala.collection.immutable.HashMap
import scala.io.Source

/**
 * Created by alex on 10/27/2015.
 */
object pokerhands {
  val orderMap = HashMap[Char, Int](('T',9),('J',10),('Q',11),('K',12),('A',13))

  def main(args: Array[String]) {
    //println(winner("8C TS KC 9H 4S 7D 2S 5D 3S AC"))
    //println(winner("5H 5C 6S 7S KD 2C 3S 8S 8D TD"))
    //println(winner("5D 8C 9S JS AC 2C 5C 7D 8S QH"))
    //println(winner("2D 9C AS AH AC 3D 6D 7D TD QD"))
    var result = Source.fromFile("C:/Users/alex/IdeaProjects/euler/eulerscala/res/poker.txt").getLines().count(winner(_)==1)
    println(result)
  }

  def readAll(): Unit = {

  }

  def winner(str: String): Int = {
    val cards = str.split(" ").map { p: String => new Card(p.charAt(1), p.charAt(0)) }
    val (hand1, hand2) = cards.splitAt(5)
    //if all same suite
    val winner = evalWinner(hand1, hand2)
    if (winner != 0) {
      winner
    } else {
      0
    }
  }

  /*High Card: Highest value card.
  One Pair: Two cards of the same value.
  Two Pairs: Two different pairs.
  Three of a Kind: Three cards of the same value.
  Straight: All cards are consecutive values.
  Flush: All cards of the same suit.
  Full House: Three of a kind and a pair.
  Four of a Kind: Four cards of the same value.
  Straight Flush: All cards are consecutive values of same suit.
  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.*/

  def evalWinner(hand1: Array[Card], hand2: Array[Card]): Int = {
    val oak1 = ofAKind(hand1)
    val oak2 = ofAKind(hand2)
    val consec1 = getConsecutive(hand1)
    val consec2 = getConsecutive(hand2)
    val house1 = isFullHouse(hand1)
    val house2 = isFullHouse(hand2)
    val sf = evalSFWinner(hand1, hand2)
    if (sf != 0) {
      return sf
    }
    val fow = fouroakwinner(oak1, oak2)
    if (fow != 0) {
      return fow
    }

    val fhw = evalfullhousewinner(hand1, hand2)
    if (fhw != 0) {
      return fhw
    }

    val flw = evalflushwinner(hand1, hand2)
    if (flw != 0) {
      return flw
    }

    val sw = straightwinner(consec1, consec2)
    if (sw != 0) {
      return sw
    }

    val thow = threeoakwinner(oak1, oak2)
    if (thow != 0) {
      return thow
    }

    val twow = twooakwinner(oak1, oak2)
    if (twow != 0) {
      return twow
    }
    return evalHighCard(hand1, hand2)
  }

  def twooakwinner(oak1: (Int, Array[Card]), oak2: (Int, Array[Card])): Int = {
    if (oak2._1 == 2 && oak1._1 == 2) {
      if (oak2._2.head.order > oak1._2.head.order) {
        2;
      } else {
        1;
      }
    } else if (oak2._1 == 2) {
      2;
    } else if (oak1._1 == 2) {
      1;
    } else {
      0
    }
  }

  def threeoakwinner(oak1: (Int, Array[Card]), oak2: (Int, Array[Card])): Int = {
    if (oak2._1 == 3 && oak1._1 == 3) {
      if (oak2._2.head.order > oak1._2.head.order) {
        2;
      } else {
        1;
      }
    } else if (oak2._1 == 2) {
      2;
    } else if (oak1._1 == 2) {
      1;
    } else {
      0
    }
  }

  def evalfullhousewinner(hand1: Array[Card], hand2: Array[Card]) = {
    val house1 = isFullHouse(hand1)
    val house2 = isFullHouse(hand2)
    if (house1 && !house2) {
      1
    } else if (!house1 && house2) {
      2
    } else if (house1 && house2) {
      val grouped1 = hand1.groupBy(_.order)
      val three1 = grouped1.filter(_._1 == 3).head._1
      val grouped2 = hand1.groupBy(_.order)
      val three2 = grouped2.filter(_._1 == 3).head._1
      if (three1 < three2) {
        2
      } else {
        1
      }
    } else {
      0
    }
  }

  def evalflushwinner(hand1: Array[Card], hand2: Array[Card]): Int = {
    val allsame1 = hand1.filter(_.suit == hand1.head.suit).size == 5
    val allsame2 = hand2.filter(_.suit == hand2.head.suit).size == 5
    if (allsame1 && !allsame2) {
      1
    } else if (!allsame1 && allsame2) {
      2
    } else {
      0
    }
  }

  def straightwinner(consec1: Int, consec2: Int): Int = {
    if (consec1 != consec2) {
      if (consec1 > consec2) {
        1;
      } else {
        2;
      }
    } else {
      0
    }
  }

  def fouroakwinner(oak1: (Int, Array[Card]), oak2: (Int, Array[Card])): Int = {
    if (oak2._1 == 4 && oak1._1 == 4) {
      if (oak2._2.head.order > oak1._2.head.order) {
        2
      } else {
        1
      }
    } else if (oak2._1 == 4) {
      2
    } else if (oak1._1 == 4) {
      1
    } else {
      0
    }
  }

  def evalHighCard(hand1: Array[Card], hand2: Array[Card]) = {
    val sort1 = hand1.map(_.order).sorted.reverse
    val sort2 = hand2.map(_.order).sorted.reverse
    var idx = 0
    def idxwinner(idx: Int) = {
      if (sort1(idx) != sort2(idx)) {
        if (sort1(idx) > sort2(idx)) {
          1
        } else {
          2
        }
      } else {
        0
      }
    }
    var ret = 0
    var continue = true
    while (idx < 5 && continue) {
      val winner = idxwinner(idx)
      if (winner != 0) {
        continue = false
        ret = winner
      }
      idx += 1
    }
    ret
  }

  def evalSFWinner(hand1: Array[Card], hand2: Array[Card]): Int = {
    val allsame1 = hand1.filter(_.suit == hand1.head.suit).size == 5
    val allsame2 = hand2.filter(_.suit == hand2.head.suit).size == 5
    val consec1 = getConsecutive(hand1)
    val consec2 = getConsecutive(hand2)
    if (allsame1 && allsame2) {
      if (consec1 > consec2) {
        1;
      } else if (consec1 < consec2) {
        2;
      } else {
        0
      }
    } else if (allsame1 && consec1 > 0) {
      1
    } else if (allsame2 && consec2 > 0) {
      2
    } else {
      0
    }
  }

  def ofAKind(hand: Array[Card]) = {
    val grouped = hand.groupBy(_.order).maxBy(_._2.length)
    (grouped._2.size, grouped._2)
  }

  def isFullHouse(hand: Array[Card]) = {
    val grouped = hand.groupBy(_.order)
    if (grouped.exists(_._1 == 2) && grouped.exists(_._1 == 3)) {
      true
    } else {
      false
    }
  }


  def getConsecutive(hand: Array[Card]) = {
    val start = hand(0).order
    if (hand(1).order == start + 1 && hand(2).order == start + 2 && hand(3).order == start + 3 &&
      hand(4).order == start + 4) {
      start
    } else {
      0
    }
  }

  class Card(val suit: Char, val orderc: Char) {
    val order = if (orderc.isDigit && orderc.asDigit < 10) {
      orderc.asDigit - 1
    } else {
      orderMap(orderc)
    }
  }

}
