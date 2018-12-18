import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of WeeklyPlan objects
 * @author Sabin Sirbu
 * @version 1.0
 */ 
public class WeeklyPlanList implements Serializable
{
   ArrayList<WeeklyPlan> weeklyPlans;
   /**
    * No-argument constructor initializing the WeeklyPlanList.
    */
   public WeeklyPlanList() {
      weeklyPlans = new ArrayList<WeeklyPlan>();
   }
   /**
    * Adds an WeeklyPlan to the list
    * @param index the weeklyPlan to add to the list
    */
   public void addWeeklyPlan(WeeklyPlan weeklyPlan) {
      weeklyPlans.add(weeklyPlan);
   }
   /**
    * Adds the WeeklyPlan object to the given position
    * @param index position in the list
    * @param weeklyplan 
    */
   public void addWeeklyPlanByIndex(int index, WeeklyPlan weeklyplan)
   {
      weeklyPlans.add(index, weeklyplan);
   }
   /**
    * Removes the WeeklyPlan from the list
    * @param weeklyPlan the weeklyPlan to delete from the list
    */
   public void remove(WeeklyPlan weeklyPlan) {
      weeklyPlans.remove(weeklyPlan);
   }
   /**
    * Removes the WeeklyPlan object from the given position in the list
    * @param index the position of the WeeklyPlan object in the list
    */
   public void remove(int index) {
      weeklyPlans.remove(index);
   }
   /**
    * Gets the index of a given WeeklyPlan object
    * @param analysis the analysis of the WeeklyPlan object
    * @param matrix the matrix of the WeeklyPlan object
    * @return the index of a given WeeklyPlan object
    */
   public int getIndex(Analysis analysis, String weekSize) 
   {
      for(int i = 0;i<weeklyPlans.size();i++) {
         WeeklyPlan temp = weeklyPlans.get(i);
         
         if (temp.getAnalysis().equals(analysis) 
               && temp.getWeekSize().equals(weekSize)) {
            return i;
         }
      }
      return -1;
   }
   /**
    * Gets a WeeklyPlan object from position index from the list. 
    * @param index the position in the list of the Student object  
    * @return the WeeklyPlan object at position index if one exists, else null
    */
   public WeeklyPlan get(int index) {
      if (index<weeklyPlans.size())
         return weeklyPlans.get(index);
      else
         return null;
   }
   /**
    * Gets how many WeeklyPlan objects are in the list
    * @return the number of WeeklyPlan objects in the list
    */
   public int size() {
      return weeklyPlans.size();
   }
   /**
    * Replaces the WeeklyPlan object at index with weeklyPlan.
    * @param weeklyPlan the weeklyPlan to replace with
    * @param index position in the list
    */
   public void set(WeeklyPlan weeklyPlan, int index) {
      weeklyPlans.set(index, weeklyPlan);
   }
   /**
    * Gets a String representation of the WeeklyPlanList.
    * @return String representation of the WeeklyPlanList
    */
   public String toString() {
      String str = "";
      for (int i = 0; i < weeklyPlans.size(); i++)
      {
         WeeklyPlan temp = weeklyPlans.get(i);
         
         str += temp + "\n";
      }
      return str;
   }
   /**
    * Compares two WeeklyPlanList objects
    * @param obj the object to compare with
    * @return true if the given object is equal to this weekly plan
    */
   public boolean equals(Object obj) {
      if (!(obj instanceof WeeklyPlanList))
         return false;
      WeeklyPlanList  temp = (WeeklyPlanList) obj;
      if (temp.size() != size())
         return false;
      return weeklyPlans.equals(temp.weeklyPlans);
   }
}
