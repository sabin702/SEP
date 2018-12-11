import java.util.ArrayList;
public class AnnualPerformanceList
{

private ArrayList <AnnualPerformance> comments;    
/*
 * A contructor which takes elements of type AnnualPerformance   
 */
public AnnualPerformanceList()
{
 comments = new ArrayList<AnnualPerformance> (comments);   
}
/**
 *  A method that adds an employee's preference to the list of preferences or perfomanceList
 * @param annualPerformance the employee's preference to add to the list
 */
public void addAnnualPerformance(AnnualPerformance annualPerformance)
{
comments.add(annualPerformance);
}
/**
 * 
 * @param annualPerformance
 */
public void removeAnnualPerformance (AnnualPerformance annualPerformance)
{
comments.remove(annualPerformance);   
}
public void sortByName()
{
   
}

public boolean equals (Object obj)
{
   if (!(obj instanceof AnnualPerformanceList))
   {
      return false;
   }
   AnnualPerformanceList temp = (AnnualPerformanceList) obj;
   if (temp.comments.equals(comments))
   {
      return true;
   }
   else
   {
      return false;
   }   

public String toString()
 {
    return comments;
    
 }
}

}
