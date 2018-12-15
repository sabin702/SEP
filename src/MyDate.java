
import java.util.GregorianCalendar;

import java.util.Calendar;
import java.util.Date;


/**
 * A class containing the current Date
 * @author sabin
 * @version 1.0
 */
public class MyDate
{
   private int day;
   private int month;
   private int year;
   private int week;
   
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
    * Returns the 
    * @return
    */
   public int getDay() {
      return day;
   }
   
   public void setDay(int day) {
      this.day = day;
   }
   
   public int getMonth() {
      return month;
   }
   
   public void setMonth(int month) {
      this.month = month;
   }
   
   public int getYear() {
      return year;
   }
   
   public void setYear(int year) {
      this.year = year;
   }
   
   public int getCurrentWeek() {
      Calendar calendar = new GregorianCalendar();
      Date currentDate = new Date();
      calendar.setTime(currentDate);
      week = calendar.get(Calendar.WEEK_OF_YEAR);
      return week;
   }
   
   public void setWeek(int week) {
      this.week = week;
   }
   
   public static MyDate today() {
      GregorianCalendar currentDate = new GregorianCalendar();
      int currentDay = currentDate.get(GregorianCalendar.DATE);
      int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
      int currentYear = currentDate.get(GregorianCalendar.YEAR);
      return new MyDate(currentDay,currentMonth, currentYear);
   }
   
   public boolean isLeapYear() {
      if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) { 
         return true;
      }
      else {
         return false;
      }
   }
   
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
   
   public void nextDay() {
      day++;
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
   
   public String toString() {
      return day + "-" + month + "-" + (year-2000);
   }
}
