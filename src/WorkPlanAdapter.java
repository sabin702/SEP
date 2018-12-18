import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkPlanAdapter
{
   private MyFileIO mfio;
   private String fileName;
   
   public WorkPlanAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }
   
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
   
   public void addWorkPlan(int index,WorkPlan workPlan)
   {
      WorkPlanList workPlans= getAllWorkPlans();
      
      workPlans.addWorkPlanByIndex(index, workPlan);

      saveWorkPlans(workPlans);
   }
}
