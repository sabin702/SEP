import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the students file, making it easy to retrieve and store information.
 * @author Sabin Sirbu
 * @version 1.0
 */

public class WeeklyPlanFileAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where weekly plans for each analysis will be saved and retrieved
    */
   
   public WeeklyPlanFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }
   
   /**
    * Uses the MyFileIO class to retrieve a WeeklyPlanList object with all weekly plans for each analysis.
    * @return a WeeklyPlanList object with all stored weekly plans for each analysis
    */
   
   public WeeklyPlanList getAllWeeklyPlans() {
      WeeklyPlanList weeklyPlans = new WeeklyPlanList();
      
      try {
         weeklyPlans = (WeeklyPlanList) mfio.readObjectFromFile(fileName);
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found");
      }
      catch (IOException e) {
         System.out.println("IO error reading files");
         e.printStackTrace();
      }
      catch (ClassNotFoundException e) {
         System.out.println("Class not found");
      }
      
      return weeklyPlans;
   }
   
   /**
    * Use the MyFileIO class to save some weekly plans.
    * @param weeklyPlans the list of weekly plans that will be saved
    */
   
   public void saveWeeklyPlans(WeeklyPlanList weeklyPlans) {
      try {
         mfio.writeToFile(fileName, weeklyPlans);
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found");
      }
      catch(IOException e) {
         System.out.println("IO Error writing the file");
      }
   }
   
   /**
    * Uses the MyFileIO class to change the weekly plan of an analysis
    * @param analysis the analysis of the weekly plan
    * @param weekSize the week type of the weekly plan
    * @param numberOfEmpployees the updated number of employees for a specific analysis
    */
   
   public void changeWeeklyPlan(Analysis analysis, String weekSize, double[] numberOfEmpployees)
   {
      WeeklyPlanList weeklyPlans = getAllWeeklyPlans();
      
      for (int i = 0; i < weeklyPlans.size(); i++)
      {
         if(weeklyPlans.get(i).getAnalysis().equals(analysis)
               && weeklyPlans.get(i).getWeekSize().equals(weekSize)) 
         {
            for (int j = 0; j < 6; j++)
            {
               weeklyPlans.get(i).setNumberOfEmployees(j, numberOfEmpployees[j]);
            }
         }
      }
      saveWeeklyPlans(weeklyPlans);
   }
   
   /**
    * Uses the MyFileIO class to add a weekly plan for an analysis at a specific position
    * @param i the position where a weekly plan will be added to the list of weekly plans
    * @param weeklyPlan the weekly plan that is going to be added to the list
    */
   
   public void addWeeklyPlan(int i,WeeklyPlan weeklyPlan)
   {
      WeeklyPlanList weeklyPlans= getAllWeeklyPlans();
      
      weeklyPlans.addWeeklyPlanByIndex(i, weeklyPlan);

      
      saveWeeklyPlans(weeklyPlans);
   }

}