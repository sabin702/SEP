import java.io.Serializable;
/**
 * A class representing a weekly plan with an analysis, week size and list of workers.
 * @author Sabin Sirbu
 * @version 1.0
 */
public class WeeklyPlan implements Serializable
{
   private Analysis analysis;
   private String weekSize;
   double[] weeklyEmployees;
   /**
    * Three-argument constructor
    * @param analysis the weekly plan's analysis
    * @param weekSize the weekly plan's week size
    * @param weeklyEmployees the weekly plan's workers
    */
   public WeeklyPlan(Analysis analysis, String weekSize, double[] weeklyEmployees) {
      this.analysis = analysis;
      this.weekSize = weekSize;
      this.weeklyEmployees = weeklyEmployees;
   }
   /**
    * Gets the weekly plan's analysis
    * @return weekly plan's analysis
    */
   public Analysis getAnalysis() {
      return analysis;
   }
   /**
    * Gets the weekly plan's week size
    * @return weekly plan's week size
    */
   public String getWeekSize() {
      return weekSize;
   }
   /**
    * Sets the weekly plan's week size
    * @param weekSize what the weekly plan's week size will be set to
    */
   public void setWeekSize(String weekSize) {
      this.weekSize = weekSize;
   }
    /**
     * Sets the analysis' number of needed employees for a week
     * @param index represents the index of a specific day(0- monday, 1- tuesday etc.)
     * @param numberOfEmployees what the number of employees is needed for the analysis
     */
   public void setNumberOfEmployees(int index, double numberOfEmployees) {
      weeklyEmployees[index] = numberOfEmployees;
   }
   /**
    * Gets the string with the weekly employees
    * @return the string with the weekly employees
    */
   public String getWeeklyEmployees() {
      String str = "";
      for (int i = 0; i < weeklyEmployees.length; i++)
      {
         str += " " +  weeklyEmployees[i];
      }
      return str;
   }
   /**
    * Gets the array with weekly employees
    * @return the array with the weekly employees
    */
   public double[] getWeeklyEmployeesArray() {
      return weeklyEmployees;
   }
   /**
    * Gets the number of the needed employees in a specific day
    * @param index represents the day of the week
    * @return the number of needed workers for a specific day
    */
   public double getWeeklyEmployee(int index) { 
      if(index<weeklyEmployees.length)
         return weeklyEmployees[index];
      else
         return -1;
   }
   /**
    * Checks if two analyses have the same number of workers in a week
    * @param temp the number of days that are going to be compared
    * @return true if two analyses have the same number of workers in a week
    */
   public boolean equalsWeeklyEmployees(double[] temp) {
      if (temp.length != weeklyEmployees.length)
         return false;
      for (int i = 0; i < temp.length; i++)
      {
         if (temp[i] != weeklyEmployees[i])
            return false;
      }
      return true;
   }
   /**
    * Checks if two analyses are the same
    * @param analysis what the analysis is going to be compared with
    * @param weekType what the week type is going to be compared with
    * @return true if the two analyses are the same
    */
   public boolean matchingAnalysisAndWeekType(Analysis analysis, String weekType) {
      if(this.analysis.equals(analysis) && weekSize.equals(weekType))
         return true;
      else
         return false;
   }
   /**
    * Returns a string representation of the weekly plan.
    * @return a string representation of the weekly plan in the format: "matrix week size analysis type "
    */
   public String toString() {
      return analysis.getMatrix() + " " 
   + weekSize + " " 
   + analysis.getAnalysisType() + " " 
   + getWeeklyEmployees();
   }
   /**
    * Compares analysis, week size and weekly employees number of two weekly plans .
    * @param obj the object to compare with
    * @return true if the given object is equal to this weekly plan
    */
   public boolean equals(Object obj) {
      if (!(obj instanceof WeeklyPlan))
         return false;
      
      WeeklyPlan temp = (WeeklyPlan) obj;
      return analysis.equals(temp.getAnalysis()) 
            && weekSize.equals(temp.getWeekSize())
            && equalsWeeklyEmployees(temp.getWeeklyEmployeesArray());
   }
   
   
}
