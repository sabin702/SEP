public class WeeklyPlan
{
   private Analysis analysis;
   private String weekSize;
   //private double numberOfEmployees;
   double[] weeklyEmployees;
   
   public WeeklyPlan(Analysis analysis, String weekSize) {
      this.analysis = analysis;
      this.weekSize = weekSize;
      //this.numberOfEmployees = numberOfEmployees;
      weeklyEmployees = new double[6];
   }
   
   public Analysis getAnalysis() {
      return analysis;
   }
   
   public String getWeekSize() {
      return weekSize;
   }
   
   public void setWeekSize(String weekSize) {
      this.weekSize = weekSize;
   }
   
   public double getNumberOfEmployees(int index) {
      //return numberOfEmployees;
      return weeklyEmployees[index];
   }
   
   public void setNumberOfEmployees(int index, double numberOfEmployees) {
      weeklyEmployees[index] = numberOfEmployees;
   }
   
   public String getWeeklyEmployees() {
      String str = "";
      for (int i = 0; i < weeklyEmployees.length; i++)
      {
         str += " " +  weeklyEmployees[i];
      }
      return str;
   }
   
   public double[] getWeeklyEmployeesArray() {
      return weeklyEmployees;
   }
   
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
   
   public String toString() {
      return analysis.getMatrix() + " " 
   + weekSize + " " 
   + analysis.getAnalysisType() + " " 
   + getWeeklyEmployees();
   }
   
   public boolean equals(Object obj) {
      if (!(obj instanceof WeeklyPlan))
         return false;
      
      WeeklyPlan temp = (WeeklyPlan) obj;
      return analysis.equals(temp.getAnalysis()) 
            && weekSize.equals(temp.getWeekSize())
            && equalsWeeklyEmployees(temp.getWeeklyEmployeesArray());
   }
   
   
}
