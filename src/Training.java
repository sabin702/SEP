import java.io.Serializable;

public class Training implements Serializable
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

   public Training(String trainingStatus, String analysis, String matrix,
         String name, String initials, String number)
   {
      this.trainingStatus = trainingStatus;
      this.analysis = new Analysis(analysis, matrix);
      this.worker = new Worker(name, number, initials);
   }

   public String getTrainingStatus()
   {
      return trainingStatus;
   }

   public void setAnalysis(Analysis analysis)
   {
      this.analysis = analysis;
   }

   public void setWorker(Worker worker)
   {
      this.worker = worker;
   }

   public Analysis getAnalysis()
   {
      return analysis;
   }

   public Worker getWorker()
   {
      return worker;
   }

   public void setTrainingStatus(String trainingStatus)
   {
      this.trainingStatus = trainingStatus;
   }

   public String toString()
   {
      return trainingStatus + " " + analysis + " " + worker;
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
