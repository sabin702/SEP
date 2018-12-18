import java.io.Serializable;
/**
 * A class representing an annual performance with a comment and worker.
 * @author Lucian Rafa
 * @version 1.0
 */ 
public class AnnualPerformance implements Serializable
{
   private String comment;
   private Worker worker;
   /**
    * Two-argument constructor.
    * @param comment the annual performance's comment
    * @param worker the annul performance's worker
    */
   public AnnualPerformance(String comment, Worker worker)
   {
      this.comment = comment;
      this.worker=worker;
   }
   /**
    * Gets the annual performance's comment.
    * @return the annual performance's comment
    */
   public String getComment()
   {
      return comment;   
   }
   
   /**
    * Comapares the initials and name of two workers
    * @param initials worker's initials
    * @param name worker's name
    * @return true if the initials and name match
    */
   public boolean matchComment(String initials, String name) {
      if(worker.getInitials().equals(initials) && worker.getName().equals(name))
         return true;
      else 
         return false;
   }
   /**
    * Gets the worker's name, number and initials
    * @return the worker's name, number and initials
    */
   public Worker getWorker()
   {
      return worker;
   }
   /**
    * Gets the comment for the worker
    * @param worker for whom we get the comment
    * @return comment if two workers are equal and empty string if they don't match
    */
   public String getComment(Worker worker) {
      if (this.worker.equals(worker))
         return comment;
      else 
         return "";
   }
/**
 * Sets the worker for the annual performance
 * @param worker what the annual performance's worker will be set to
 */
   public void setWorker(Worker worker)
   {
      this.worker = worker;
   }
/**
 * Sets the annual performance's comment
 * @param comment what the annual performance's comment will be set to
 */
   public void setComment(String comment)
   {
      this.comment=comment;
   }/**
    * Returns a string representation of the annual performance.
    * @return a string representation of the annual performance in the format: "worker comment"
    */
   public String toString()
   {
      return worker + ": " + comment; 
   }
   /**
    * Compares comments
    * @param obj the object to compare with
    * @return true if the given object is equal to this annual preference
    */
   public boolean equals(Object obj)     
   {
      if (!(obj instanceof AnnualPerformance))
      {
         return false;
      }
      AnnualPerformance temp = (AnnualPerformance) obj;
      return temp.comment.equals(comment);
   }

}

