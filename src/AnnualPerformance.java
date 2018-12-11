public class AnnualPerformance
{
private String comment;

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
return comment; 

}
public boolean equals(Object obj)     
{
   if (!(obj instanceof AnnualPerformance))
   {
      return false;
   }
   AnnualPerformance temp = (AnnualPerformance) obj;
   if (temp.comment.equals(comment))
   {
      return true;
   }
   else
   {
      return false;
   }
}




}

