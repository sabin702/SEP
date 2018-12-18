
import java.util.GregorianCalendar;

import java.util.Calendar;
import java.util.Date;


/**
 * A class containing the current Date
 * @author Sabin Sirbu
 * @version 1.0
 */
public class MyDate
{
   private int day;
   private int month;
   private int year;
   private int week;
   /**
    * Three-argument constructor.
    * @param day the date's day
    * @param month the date's month
    * @param year the date's year
    */
   public MyDate(int day, int month, int year) {
      this.day = day;
      this.month = month;
      this.year = year;
   }
   /**
    * No argument constructor initializing the instances day, month and year with current date values
    */
   public MyDate() {
      day = today().getDay();
      month = today().getMonth();
      year = today().getYear();
   }
   /**
    * Gets the date's day 
    * @return the date's day
    */
   public int getDay() {
      return day;
   }
   /**
    * Sets the date's day
    * @param day what the date's day will be set to
    */
   public void setDay(int day) {
      this.day = day;
   }
   /**
    * Gets the date's month
    * @return the date's month
    */
   public int getMonth() {
      return month;
   }
   /**
    * Sets the date's month
    * @param month what the date's month will be set to
    */
   public void setMonth(int month) {
      this.month = month;
   }
   /**
    * Gets the date's year
    * @return the date's year
    */
   public int getYear() {
      return year;
   }
   /**
    * Sets the date's year
    * @param year what the date's year will be set to
    */
   public void setYear(int year) {
      this.year = year;
   }
   /**
    * Gets the date's week
    * @return the date's week
    */
   public int getCurrentWeek() {
      Calendar calendar = new GregorianCalendar();
      Date currentDate = new Date();
      calendar.setTime(currentDate);
      week = calendar.get(Calendar.WEEK_OF_YEAR);
      return week;
   }
   /**
    * Sets the date's week
    * @param week the date's week will be set to
    */
   public void setWeek(int week) {
      this.week = week;
   }
   /**
    * Returns the today's date (day,month,year)
    * @return Today's date
    */
   public static MyDate today() {
      GregorianCalendar currentDate = new GregorianCalendar();
      int currentDay = currentDate.get(GregorianCalendar.DATE);
      int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
      int currentYear = currentDate.get(GregorianCalendar.YEAR);
      return new MyDate(currentDay,currentMonth, currentYear);
   }
   /**
    * Check's if the date's year is a leap year
    * @return true if the date's year is a leap year
    */
   public boolean isLeapYear() {
      if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) { 
         return true;
      }
      else {
         return false;
      }
   }
   /**
    * Shows how many days are in the given month
    * @return amount of the days in the given month
    */
   public int daysInMonth() {
      while(month>12) {
         month = month-12;
         year = year + 1;
         }
      if(month>=1 && month<=12) {
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ) {
         return 31;
      }
      else if (month == 4 || month == 6 || month == 9 || month == 11 ) {
         return 30;
      }
      else if(month == 2) {
         if(isLeapYear() == true) {
            return 29;
         }
         else {
            return 28;
         }
      }
      else {
         return 0;
      }
      }
      else {
        return 0;
      }
   }
   /**
    * Gets the name of the given day of the week
    * @return the name of the given day of the week
    */
   public String dayOfWeek() {
      int month1 = month;
      int year1 = year;
      if (month1 == 1 || month1 == 2) {
         month1+=12;
         year1-=1;
      }
      int dayOfWeek;
      int yearOfTheCentury = year1 % 100;
      int century = year1 / 100;
      dayOfWeek = (day+(13*(month1+1))/5+ yearOfTheCentury + (yearOfTheCentury/4) + (century/4) + 5*century)%7;
      switch(dayOfWeek) {
         case 0:
         return "Saturday";
      
         case 1:
         return "Sunday";
      
         case 2:
         return "Monday";
      
         case 3:
         return "Tuesday";
      
         case 4:
         return "Wednesday";
      
         case 5:
         return "Thursday";
      
         case 6:
         return "Friday";
      
      default:
         return "Error 404 - Day not found"; 
      }
      
   }
  
   /**
    * Adds a specific number of days to the current date
    * @param days is the amount of days that are added to the current date
    */
   public void nextDays(int days) {
      day += days;
      if(day > daysInMonth()){
         while(day > daysInMonth()){
            setDay(day - daysInMonth());
            }
         setMonth(month + 1);
         }
         if(month > 12) {
            while(month > 12){
               setMonth(month - 12);
               }
            setYear(year + 1);
         } 
   }
   /**
    * Sets the today's date to the next day's date
    * @return the next day's date
    */
   public MyDate nextDate() {
      day ++;
      if(day > daysInMonth()){
         while(day > daysInMonth()){
            setDay(day - daysInMonth());
            }
         setMonth(month + 1);
         }
         if(month > 12) {
            while(month > 12){
               setMonth(month - 12);
               }
            setYear(year + 1);
         } 
         return new MyDate(day, month, year);
   }
   /**
    * Returns a string representation of the date.
    * @return a string representation of the date in the format: "day-month-year"
    */
   public String toString() {
      return day + "-" + month + "-" + (year-2000);
   }
}
