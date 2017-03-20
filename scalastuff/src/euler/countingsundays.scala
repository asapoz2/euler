package euler

object countingsundays {
  def main(args: Array[String]): Unit = {
    val firstDay = new MyDate(1, WeekDay.Mon, Month.Jan, 1900)
    var currDay = firstDay
    while(currDay.day!=1 || currDay.year!=1901){
      currDay=currDay.nextDay()
    }
    println(currDay.dayofweek+" is the day of the week of " + currDay.toString())
    var result = 0
    while(currDay.day!=31 || currDay.year!=2000||currDay.month!=Month.Dec){
      currDay=currDay.nextDay()
      if(currDay.isSunday&&currDay.day==1){
        result+=1
      }
    }
    println("sundays: "+result+" is the day of the week of " + currDay.toString())
    
  }
  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
    def nextDays = Map(Mon -> Tue, Tue -> Wed, Wed -> Thu, Thu -> Fri, Fri -> Sat, Sat -> Sun, Sun -> Mon)
    def nextDay(day: WeekDay.WeekDay) = {
      nextDays(day)
    }
  }
  object Month extends Enumeration {
    type Month = Value
    val Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec = Value
    def nextMonths = Map(Jan -> Feb, Feb -> Mar, Mar -> Apr, Apr -> May,
      May -> Jun, Jun -> Jul, Jul -> Aug, Aug -> Sep, Sep -> Oct, Oct -> Nov, Nov -> Dec, Dec -> Jan)
    def nextMonth(month: Month.Month) = {
      nextMonths(month)
    }
    val thirty = Array(Sep, Apr, Jun, Nov)

    def daysInMonth(month: Month.Month, year: Int) = {
      if (thirty.contains(month)) {
        30
      } else if (month != Feb) {
        31
      } else {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
          29
        } else {
          28
        }
      }
    }
  }
  class MyDate(day1: Int, dayofweek1: WeekDay.WeekDay, mont1h: Month.Month, year1: Int) {
    val day = day1
    val dayofweek = dayofweek1
    val month = mont1h
    val year = year1
    def nextDay() = {
      val nextWDay = WeekDay.nextDay(dayofweek)
      var nextDay = day + 1
      if (Month.daysInMonth(month, year) < nextDay) {
        nextDay = 1
        val nextMonth = Month.nextMonth(month)
        if (nextMonth == Month.Jan) {
          val nextYear = year + 1
          new MyDate(nextDay, nextWDay, nextMonth, nextYear)
        } else {
          new MyDate(nextDay, nextWDay, nextMonth, year)
        }
      } else {
        new MyDate(nextDay, nextWDay, month, year)
      }
    }
    def dayFromNextWeek() = {
      var nextDay = day + 7
      if (Month.daysInMonth(month, year) < nextDay) {
        nextDay = (nextDay-Month.daysInMonth(month, year))
        val nextMonth = Month.nextMonth(month)
        if (nextMonth == Month.Jan) {
          val nextYear = year + 1
          new MyDate(nextDay, dayofweek, nextMonth, nextYear)
        } else {
          new MyDate(nextDay, dayofweek, nextMonth, year)
        }
      } else {
        new MyDate(nextDay, dayofweek, month, year)
      }
    }
    def isSunday = {
      dayofweek == WeekDay.Sun
    }
    def equals(other: MyDate): Boolean = {
      (this.day == other.day) && (this.month == other.month) && 
      (this.dayofweek == other.dayofweek) && (this.year == other.year)
    }
    def lessThan(other: MyDate): Boolean = {
      if(this.year < other.year){
        true
      }else if(this.year==other.year){
        if(this.month<other.month){
          true
        }else if(this.month==other.month){
          this.day<other.day
        }else{
          false
        }
      }else{
        false
      }
    }
    override def toString()={
      "day:"+day+", dayofweek:"+dayofweek+", month:"+month+", year:"+year
    }
  }
}