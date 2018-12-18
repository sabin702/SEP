import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Annual Performance objects
 * @author Lucian Rafa, Aleksander Bialik
 * @version 1.0
 */
public class AnnualPerformanceList implements Serializable
{

   private ArrayList<AnnualPerformance> comments;

   /**
    * No-argument constructor initializing the AnnualPerformanceList
    */
   public AnnualPerformanceList()
   {
      comments = new ArrayList<AnnualPerformance>();
   }

   /**
    * Adds an AnnualPerformance to the list
    * @param annualPerformance the annualPerformance to add to the list
    */
   public void addAnnualPerformance(AnnualPerformance annualPerformance)
   {
      comments.add(annualPerformance);
   }

   /**
    * Removes the AnnualPerformance from the list
    * @param annual the annualPerformance to delete from the list
    */
   
   public void removeAnnualPerformance(AnnualPerformance annual)
   {
      comments.remove(annual);
   }
/**
 * Gets the number of the AnnualPerformance objects in the list
 * @return the number of the AnnualPerformance objects in the list
 */
   public int getSize()
   {
      return comments.size();
   }
   /**
    * Removes the AnnualPerformance object from the given position in the list
    * @param i the position of the AnnualPerformance object in the list
    */
   public void removeIndex(int i)
   {
      comments.remove(i);
   }
/**
 * Gets the AnnualPerformance object from a given position in the list
 * @param index the position of the AnnualPerformance object in the list
 * @return the AnnualPerformance object from a given position in the list 
 */
   public AnnualPerformance getAnnualPerformance(int index)
   {
      return comments.get(index);
   }
   
   
   /**
    * Gets the comment for a worker with given initials and name
    * @param initials the worker's initials
    * @param name the worker's name
    * @return the comment for a worker with given initials and name
    */
   public String getComment(String initials, String name) {
      for(int i = 0;i<comments.size();i++) {
         if(comments.get(i).matchComment(initials, name))
            return comments.get(i).getComment();
      }
      return "nothing";
   }
   /**
    * Compares two AnnualPerformanceList  objects
    * @param obj the object to compare with
    * @return true if the given object is equal to this annual performance
    */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof AnnualPerformanceList))
      {
         return false;
      }
      AnnualPerformanceList temp = (AnnualPerformanceList) obj;
      return temp.comments.equals(comments);

   }
   /**
    * Gets a String representation of the AnnualPerformanceList.
    * @return String representation of the AnnualPerformanceList
    */
   public String toString()
   {
      String str = "";
      for (int i = 0; i < comments.size(); i++)
         str += comments.get(i);
      return str;
   }
}
