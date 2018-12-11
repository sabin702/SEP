
public class TestClass
{
   public static void main(String[] args)
   {
      MyDate date = new MyDate();
      int weekNumber = date.getCurrentWeek();
      System.out.println(weekNumber);
      int i = 0;
      do {
         System.out.print(date + " ");
         date.nextDay();
 
         if(date.getMonth() == 12 && date.getDay() == 31) {
               if(date.dayOfWeek().equals("Monday") || date.dayOfWeek().equals("Tuesday") || date.dayOfWeek().equals("Wednesday"))
                  weekNumber = 0; 
         }
         if (date.dayOfWeek().equals("Monday")) {
               weekNumber++;
               System.out.println();
               System.out.println(weekNumber);
         } 
         i++;
      }while(i<60);
   }
}
