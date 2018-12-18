import java.io.Serializable;
public class AnnualPerformance implements Serializable
{
   private String comment;
   private Worker worker;

   public AnnualPerformance(String comment, Worker worker)
   {
      this.comment = comment;
      this.worker=worker.copy();
   }
   
   public String getComment()
   {
      return comment;   
   }
   
   //This method was added by Sabin today
   public boolean matchComment(String initials, String name) {
      if(worker.getInitials().equals(initials) && worker.getName().equals(name))
         return true;
      else 
         return false;
   }
   
   public Worker getWorker()
   {
      return worker;
   }
   
   public String getComment(Worker worker) {
      if (this.worker.equals(worker))
         return comment;
      else 
         return "";
   }

   public void setWorker(Worker worker)
   {
      this.worker = worker;
   }

   public void setComment(String comment)
   {
      this.comment=comment;
   }
   public String toString()
   {
      return worker + ": " + comment; 
   }
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

