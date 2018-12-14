import java.io.Serializable;
import java.util.ArrayList;

public class AnalysisList implements Serializable
{

   ArrayList<Analysis> analysises;

   public AnalysisList()
   {
      analysises = new ArrayList<Analysis>();
   }

   public void addAnalysis(Analysis index)
   {
      analysises.add(index);
   }
   public void add(Analysis analysis) {
      analysises.add(analysis);
   }

   public void removeAnalysis(Analysis anal)
   {
      analysises.remove(anal);
   }

   public Analysis get(int index)
   {
      return analysises.get(index);
   }
   
   /**
    * Gets a Student object with the given first name and last name from the list.
    * @param firstName the first name of the Student object
    * @param lastName the last name of the Student object
    * @return the Student object with the given first name and last name if one exists, else null
    */
   public Analysis get(String analysis)
   {
      for(int i = 0; i<analysises.size(); i++)
      {
         Analysis temp = analysises.get(i);
         
         if(temp.getAnalysisType().equals(analysis))
         {
            return temp;
         }
      }
      
      return null;
   }
   
   /**
    * Gets how many Student objects are in the list.
    * @return the number of Student objects in the list
    */
   public int size()
   {
      return analysises.size();
   }

   public String toString()
   {
      return analysises.toString();
   }

   public boolean equals(Object obj)
   {
      AnalysisList other = (AnalysisList) obj;

      if (other.analysises.size() != this.analysises.size())
      {
         return false;
      }
      else
      {
         for (int i = 0; i < this.analysises.size(); i++)
         {
            if ((!other.analysises.get(i).equals(this.analysises.get(i))))
            {
               return false;
            }
         }
         return true;
      }
   }

}
