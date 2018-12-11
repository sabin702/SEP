public class AnnualPerformance
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

