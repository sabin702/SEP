import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Workplan objects.
 * @author Sabin Sirbu
 * @version 1.0
 */

public class WorkPlanList implements Serializable
{
   ArrayList<WorkPlan> workPlans;
   
   /**
    * No-argument constructor initializing the WorkPlanList.
    */
   
   public WorkPlanList() {
      workPlans = new ArrayList<WorkPlan>();
   }
   
   /**
    * Adds a Workplan to the list.
    * @param workPlan the work plan to add to the list
    */ 
   
   public void addWorkPlan(WorkPlan workPlan) {
      workPlans.add(workPlan);
   }
   
   /**
    * Adds a Workplan object at index to the list.
    * @param index the position in the list that will be added
    * @param workPlan the work plan to add to the list
    */ 
   
   public void addWorkPlanByIndex(int index, WorkPlan workPlan)
   {
      workPlans.add(index, workPlan);
   }
   
   /**
    * Removes a WorkPlan from the list.
    * @param workPlan the work plan to be removed from the list
    */ 
   
   public void remove(WorkPlan workPlan) {
      workPlans.remove(workPlan);
   }
   
   /**
    * Removes a Workplan object at index from the list.
    * @param index the position in the list that will be removed
    */ 
   
   public void remove(int index) {
      workPlans.remove(index);
   }

   /**
    * Gets a WorkPlan object from position index from the list. 
    * @param index the position in the list of the WorkPlan object  
    * @return the Workplan object at position index if one exists, else null
    */
   
   public WorkPlan get(int index) {
      if (index<workPlans.size())
         return workPlans.get(index);
      else
         return null;
   }
   
   /**
    * Gets the index of a WorkPlan object with the given initials, name and analysis list.
    * @param initials the initials of the employee in the WorkPlan object
    * @param name the name of the employee in the WorkPlan object
    * @param analyses the analyses list in the WorkPlan object
    * @return the index of the Student object with the given first name and last name if one exists, else -1
    */
   
   public int getIndex(String initials, String name, String[] analyses) 
   {
      for(int i = 0;i<workPlans.size();i++) {
         WorkPlan temp = workPlans.get(i);
         
         if (temp.getWorkerInitials().equals(initials)
               && temp.getWorkerName().equals(name)
               && temp.equalAnalyses(analyses))
            return i;
      }
      return -1;
   }
   
   /**
    * Gets how many WorkPlan objects are in the list.
    * @return the number of WorkPlan objects in the list
    */
   
   public int size() {
      return workPlans.size();
   }
   
   /**
    * Replaces the WorkPlan object at index with workPlan.
    * @param workPlan the work plan to replace with
    * @param index the position in the list that will be replaced
    */
   
   public void set(WorkPlan workPlan, int index) {
      workPlans.set(index, workPlan);
   }
   
   /**
    * Gets a String representation of the WorkPlanList.
    * @return a String containing information about all WorkPlan objects in the list - each WorkPlan object followed by a new line character    
    */
   
   public String toString() {
      String str = "";
      for (int i = 0; i < workPlans.size(); i++)
      {
         WorkPlan temp = workPlans.get(i);
         
         str += temp + "\n";
      }
      return str;
   }
   
   /**
    * Compares size and elements of WorkPlanList objects.
    * @param obj the object to compare with
    * @return true if the given object is equal to this work plan list
    */
   
   public boolean equals(Object obj) {
      if (!(obj instanceof WorkPlanList))
         return false;
      WorkPlanList  temp = (WorkPlanList) obj;
      if (temp.size() != size())
         return false;
      return workPlans.equals(temp.workPlans);
   } 
}
