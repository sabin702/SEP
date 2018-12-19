import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the students file, making it easy to retrieve and store information.
 * @author Sabin Sirbu and Lucian Rafa
 * @version 1.0
 */

public class WorkPlanAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where work plans for each employee will be saved and retrieved
    */
   
   public WorkPlanAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }
   
   /**
    * Uses the MyFileIO class to retrieve a WorkPlanList object with all work plans for each employee.
    * @return a WorkPlanList object with all stored work plans for each employee
    */
   
   public WorkPlanList getAllWorkPlans() {
      WorkPlanList workPlans = new WorkPlanList();
      
      try {
         workPlans = (WorkPlanList) mfio.readObjectFromFile(fileName);
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
      
      return workPlans;
   }
   
   /**
    * Use the MyFileIO class to save some work plans.
    * @param workPlans the list of work plans that will be saved
    */
   
   public void saveWorkPlans(WorkPlanList workPlans) {
      try {
         mfio.writeToFile(fileName, workPlans);
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found");
      }
      catch(IOException e) {
         System.out.println("IO Error writing the file");
      }
   }
   
   /**
    * Uses the MyFileIO class to change the work plan of an employee
    * @param initials the initials of the employee in the weekly plan
    * @param name the name of the employee in the weekly plan
    * @param analyses the updated  types of analyses for an employee in a week
    */
   
   public void changeWorkPlan(String initials, String name, String[] analyses)
   {
      WorkPlanList workPlans = getAllWorkPlans();
      
      for (int i = 0; i < workPlans.size(); i++)
      {
         if(workPlans.get(i).getWorkerInitials().equals(initials) 
               && workPlans.get(i).getWorkerName().equals(name)) 
         {
           workPlans.get(i).setAnalyses(analyses);
         }
      }
   }
   
   /**
    * Uses the MyFileIO class to add a work plan for an employee at index
    * @param index the position where a work plan will be added to the list of work plans
    * @param workPlan the work plan that is going to be added to the list
    */
   
   public void addWorkPlan(int index,WorkPlan workPlan)
   {
      WorkPlanList workPlans= getAllWorkPlans();
      
      workPlans.addWorkPlanByIndex(index, workPlan);

      saveWorkPlans(workPlans);
   }
}
