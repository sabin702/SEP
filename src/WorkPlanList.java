import java.io.Serializable;
import java.util.ArrayList;

public class WorkPlanList implements Serializable
{
   ArrayList<WorkPlan> workPlans;
   
   public WorkPlanList() {
      workPlans = new ArrayList<WorkPlan>();
   }
   
   public void addWorkPlan(WorkPlan workPlan) {
      workPlans.add(workPlan);
   }
   
   public void addWorkPlanByIndex(int index, WorkPlan workPlan)
   {
      workPlans.add(index, workPlan);
   }
   
   public void remove(WorkPlan workPlan) {
      workPlans.remove(workPlan);
   }
   
   public void remove(int index) {
      workPlans.remove(index);
   }
   
   public WorkPlan get(int index) {
      if (index<workPlans.size())
         return workPlans.get(index);
      else
         return null;
   }
   
   public int getIndex(String initials, String name, AnnualPerformance annualPerformance, String[] analyses) 
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
   
   public int size() {
      return workPlans.size();
   }
   
   public void set(WorkPlan workPlan, int index) {
      workPlans.set(index, workPlan);
   }
   
   public String toString() {
      String str = "";
      for (int i = 0; i < workPlans.size(); i++)
      {
         WorkPlan temp = workPlans.get(i);
         
         str += temp + "\n";
      }
      return str;
   }
   
   public boolean equals(Object obj) {
      if (!(obj instanceof WorkPlanList))
         return false;
      WorkPlanList  temp = (WorkPlanList) obj;
      if (temp.size() != size())
         return false;
      return workPlans.equals(temp.workPlans);
   } 
}
