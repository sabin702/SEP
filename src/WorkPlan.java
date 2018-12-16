import java.io.Serializable;
import java.util.ArrayList;

public class WorkPlan implements Serializable
{
   private Worker worker;
   private TrainingList trainings;
   private AnnualPerformance annualPerformance;
   private ArrayList<MyDate> dates;
   
   public WorkPlan(Worker worker, AnnualPerformance annualPerformance, TrainingList trainings) {
      this.worker = worker;
      this.annualPerformance = annualPerformance;
      this.trainings = trainings;
      dates = new ArrayList<MyDate>();
   }
   
   public Worker getWorker() {
      return worker;
   }
   
   public AnnualPerformance getAnnualPerformance() {
      return annualPerformance;
   }
   
   public Analysis getAnalysis(int index) {
      return trainings.getTraining(index).getAnalysis();
   }
   
   public TrainingList getTrainings() {
      return trainings;
   }
   
   public String getAnalysis() {
      String str = "";
      for (int i = 0; i < trainings.getSize(); i++) {
         str += trainings.getTraining(i).getAnalysis().getAnalysisType()
               + " (" + trainings.getTraining(i).getAnalysis().getMatrix() + ") and ";
      }
      return str;
   }
   
   public void setTrainings(TrainingList trainings) {
      this.trainings = trainings;
   }
   
   public String toString() {
      return worker + " " + getAnalysis();
   }
   
   public boolean equals(Object obj) {
      if(!(obj instanceof WorkPlan))
         return false;
      WorkPlan temp = (WorkPlan) obj;
      return worker.equals(temp.getWorker()) 
            && annualPerformance.equals(temp.getAnnualPerformance())
            && trainings.equals(temp.trainings);
   }
   
}
