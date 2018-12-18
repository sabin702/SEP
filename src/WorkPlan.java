import java.io.Serializable;
import java.util.ArrayList;

public class WorkPlan implements Serializable
{
   private String initials;
   private String name;
   String[] analyses;
   private ArrayList<MyDate> dates;
   
   public WorkPlan(String initials, String name, String[] analyses) {
      //this.annualPerformance = annualPerformance;
      this.initials = initials;
      this.name = name;
      this.analyses = analyses;
      dates = new ArrayList<MyDate>();
   }
   
   public String getWorkerInitials() {
      return initials;
   }
   
   public String getWorkerName() {
      return name;
   }
   /*
   public AnnualPerformance getAnnualPerformance() {
      return annualPerformance;
   }*/
   
   public String getAnalysis(int index) {
      if (index<analyses.length)
         return analyses[index];
      else 
         return "Analyse";
   }
   
   public String[] getAnalyses() {
      return analyses;
   }
   
   public String getAnalysisString() {
      String str = "";
      for (int i = 0; i < analyses.length; i++) {
         str += analyses[i] + " ";
      }
      return str;
   }
   
   public void setAnalyses(String[] analyses) {
      this.analyses = analyses;
   }
   
   public boolean equalAnalyses(String[] analyses) {
      if (this.analyses.length != analyses.length)
         return false;
      for(int i = 0;i<analyses.length;i++) {
         if(this.analyses[i].equals(analyses[i]))
            return true;
      }
      return false;
   }
   
   public String toString() {
      return initials + " " + name + " " + getAnalyses();
   }
   
   public boolean equals(Object obj) {
      if(!(obj instanceof WorkPlan))
         return false;
      WorkPlan temp = (WorkPlan) obj;
      return initials.equals(temp.getWorkerInitials())
            && initials.equals(temp.getWorkerName())
            && equalAnalyses(temp.analyses);
   }
   
}
