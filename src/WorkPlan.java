import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing the analyses on which an employee is going to work on a week.
 * @author Sabin Sirbu
 * @version 1.0
 */ 

public class WorkPlan implements Serializable
{
   private String initials;
   private String name;
   String[] analyses;
   private ArrayList<MyDate> dates;
   
   /**
    * Three-argument constructor.
    * @param initials the employee's initials
    * @param name the employee's name
    * @param analyses a list with all the analyses on which a worker is going to work on
    */
   
   public WorkPlan(String initials, String name, String[] analyses) {
      //this.annualPerformance = annualPerformance;
      this.initials = initials;
      this.name = name;
      this.analyses = analyses;
      dates = new ArrayList<MyDate>();
   }
   
   /**
    * Gets the employee's initials.
    * @return the employee's initials
    */

   
   public String getWorkerInitials() {
      return initials;
   }
   
   /**
    * Gets the employee's name.
    * @return the employee's name
    */

   
   public String getWorkerName() {
      return name;
   }
   
   /**
    * Gets an analysis on a specific index.
    * @return an analysis at a specific index
    */

   
   public String getAnalysis(int index) {
      if (index<analyses.length)
         return analyses[index];
      else 
         return "Analyse";
   }
   
   /**
    * Gets a list with all the analysis on which the employee is going to work on.
    * @return a list with all the analysis on which the employee is going to work on
    */

   public String[] getAnalyses() {
      return analyses;
   }
   
   /**
    * Gets a string with all the analyses that were assigned to a worker in a week.
    * @return a string with all the analyses that were assigned to a worker in a week
    */
   
   public String getAnalysisString() {
      String str = "";
      for (int i = 0; i < analyses.length; i++) {
         str += analyses[i] + " ";
      }
      return str;
   }
   
   /**
    * Sets the analysis list 
    * @param analyses what the analysis list will be set to
    */
   
   public void setAnalyses(String[] analyses) {
      this.analyses = analyses;
   }
   
   /**
    * Compares length and elements of two analyses lists.
    * @param analyses the analysis list to compare with
    * @return true if the given analysis list is equal to this analysis list
    */
   
   public boolean equalAnalyses(String[] analyses) {
      if (this.analyses.length != analyses.length)
         return false;
      for(int i = 0;i<analyses.length;i++) {
         if(this.analyses[i].equals(analyses[i]))
            return true;
      }
      return false;
   }
   
   /**
    * Returns a string representation of the employee's work plan.
    * @return a string representation of the employee's work plan in the format: "initials name analysisType analysisType ..."
    */
   
   public String toString() {
      return initials + " " + name + " " + getAnalyses();
   }
   
   /**
    * Compares initials, name and analyses lists of two work plans.
    * @param obj the object to compare with
    * @return true if the given object is equal to this work plan
    */
   
   public boolean equals(Object obj) {
      if(!(obj instanceof WorkPlan))
         return false;
      WorkPlan temp = (WorkPlan) obj;
      return initials.equals(temp.getWorkerInitials())
            && initials.equals(temp.getWorkerName())
            && equalAnalyses(temp.analyses);
   }
   
}
