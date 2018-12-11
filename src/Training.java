import javafx.concurrent.Worker;

public class Training
{
   private String trainingStatus;
   private Analysis analysis;
   private Worker worker;

   public Training(String trainingStatus, Analysis analysis, Worker worker)
   {
      this.trainingStatus = trainingStatus;
      this.analysis = analysis;
      this.worker = worker;
   }

   public String getTrainingStatus()
   {
      return trainingStatus;
   }

   public void setTrainingStatus(String trainingStatus)
   {
      this.trainingStatus = trainingStatus;
   }

   public String toString()
   {
      return "Training status: " + trainingStatus + "\n" + analysis.toString()
            + worker.toString();
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Training))
      {
         return false;
      }
      Training other = (Training) obj;
      return trainingStatus == other.trainingStatus && analysis.equals(obj)
            && worker.equals(obj);
   }
}
