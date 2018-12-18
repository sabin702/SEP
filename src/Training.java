import java.io.Serializable;
/**
 * A class representing a training with a training status, worker and analysis.
 * @author Aleksander Bialik
 * @version 1.0
 */ 
public class Training implements Serializable
{
   private String trainingStatus;
   private Analysis analysis;
   private Worker worker;
/**
 * Three-argument constructor.
 * @param trainingStatus the training's status
 * @param analysis the training's analysis
 * @param worker the training's worker
 */
   public Training(String trainingStatus, Analysis analysis, Worker worker)
   {
      this.trainingStatus = trainingStatus;
      this.analysis = analysis;
      this.worker = worker;
   }
/**
 * Five-argument constructor
 * @param trainingStatus the training's status
 * @param analysis the analysis' type
 * @param matrix the analysis's matrix
 * @param name the worker's name
 * @param initials the worker's initials
 * @param number the worker's number
 */
   public Training(String trainingStatus, String analysis, String matrix,
         String name, String initials, String number)
   {
      this.trainingStatus = trainingStatus;
      this.analysis = new Analysis(analysis, matrix);
      this.worker = new Worker(name, number, initials);
   }
/**
 * Gets the training's status
 * @return the training's status
 */
   public String getTrainingStatus()
   {
      return trainingStatus;
   }
/**
 * Sets the training's analysis
 * @param analysis what the training's analysis will be set to
 */
   public void setAnalysis(Analysis analysis)
   {
      this.analysis = analysis;
   }
/**
 * Sets the training's worker
 * @param worker what the training's worker will be set to
 */
   public void setWorker(Worker worker)
   {
      this.worker = worker;
   }
/**
 * Gets the training's analysis
 * @return the training's analysis
 */
   public Analysis getAnalysis()
   {
      return analysis;
   }
/**
 * Gets the training's worker
 * @return the training's worker
 */
   public Worker getWorker()
   {
      return worker;
   }
/**
 * Sets the training's status
 * @param trainingStatus what the training's status will be set to 
 */
   public void setTrainingStatus(String trainingStatus)
   {
      this.trainingStatus = trainingStatus;
   }
   /**
    * Returns a string representation of the training.
    * @return a string representation of the training in the format: "worker | analysis | trainingStatus"
    */
   public String toString()
   {
      return worker+" | "+analysis+" | "+trainingStatus;
   }
   /**
    * Compares training status, analysis and worker of two trainings.
    * @param obj the object to compare with
    * @return true if the given object is equal to this training
    */
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
