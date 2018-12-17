import java.io.Serializable;
import java.util.ArrayList;

public class WeeklyPlanList implements Serializable
{
   ArrayList<WeeklyPlan> weeklyPlans;
   
   public WeeklyPlanList() {
      weeklyPlans = new ArrayList<WeeklyPlan>();
   }
   
   public void addWeeklyPlan(WeeklyPlan weeklyPlan) {
      weeklyPlans.add(weeklyPlan);
   }
   
   public void remove(WeeklyPlan weeklyPlan) {
      weeklyPlans.remove(weeklyPlan);
   }
   
   public void remove(int index) {
      weeklyPlans.remove(index);
   }
   
   public int getIndex(Analysis analysis, String weekSize, double[] weeklyNumber) 
   {
      for(int i = 0;i<weeklyPlans.size();i++) {
         WeeklyPlan temp = weeklyPlans.get(i);
         
         if (temp.getAnalysis().equals(analysis) 
               && temp.getWeekSize().equals(weekSize)
               && temp.equalsWeeklyEmployees(weeklyNumber))
            return i;
      }
      return -1;
   }
   
   public WeeklyPlan get(int index) {
      if (index<weeklyPlans.size())
         return weeklyPlans.get(index);
      else
         return null;
   }
   
   public int size() {
      return weeklyPlans.size();
   }
   
   public void set(WeeklyPlan weeklyPlan, int index) {
      weeklyPlans.set(index, weeklyPlan);
   }
   
   public String toString() {
      String str = "";
      for (int i = 0; i < weeklyPlans.size(); i++)
      {
         WeeklyPlan temp = weeklyPlans.get(i);
         
         str += temp + "\n";
      }
      return str;
   }
   
   public boolean equals(Object obj) {
      if (!(obj instanceof WeeklyPlanList))
         return false;
      WeeklyPlanList  temp = (WeeklyPlanList) obj;
      if (temp.size() != size())
         return false;
      return weeklyPlans.equals(temp.weeklyPlans);
   }
}
