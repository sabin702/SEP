
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
         if (date.dayOfWeek() == "Monday")
         {
            weekNumber++;
            System.out.println();
            System.out.println(weekNumber);
         }
         i++;
      }while(i<30);
   }
}
