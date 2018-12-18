
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Analysis objects
 * @author Aleksander Bialik
 * @version 1.0
 */ 
public class AnalysisList implements Serializable
{

   ArrayList<Analysis> analysises;
/**
 * No-argument constructor initializing the AnalysisList.
 */
   public AnalysisList()
   {
      analysises = new ArrayList<Analysis>();
   }
/**
 * Adds an Analysis to the list
 * @param index the analysis with given index to add to the list
 */
   public void addAnalysis(Analysis index)
   {
      analysises.add(index);
   }
   /**
    * Adds an Analysis to the list
    * @param analysis the analysis to add to the list
    */
   public void add(Analysis analysis) {
      analysises.add(analysis);
   }
/**
 * Removes an Analysis from the list
 * @param analy the analysis to delete from the list
 */
   public void removeAnalysis(Analysis analy)
   {
      analysises.remove(analy);
   }
   /**
    * Gets the index of a given Analysis object
    * @param analname name of the Analysis object
    * @param matrix matrix of the Analysis object
    * @return the index of a given Analysis object
    */
   public int getIndex(String analname, String matrix)
   {
      for(int i = 0; i<analysises.size(); i++)
      {
         Analysis temp = analysises.get(i);
         
         if(temp.getAnalysisType().equals(analname) && temp.getMatrix().equals(matrix))
         {
            return i;
         }
      }  
      return -1;
   }
   /**
    * Gets an Analysis object from position index from the list. 
    * @param index the position in the list of the Analysis object
    * @return the Analysis object at position index
    */
   public Analysis get(int index)
   {
      return analysises.get(index);
   }
   
   /**
    * Gets an Analysis object with the given analysis type
    * @param analysis analysis type of the Analysis object
    * @return the Analysis object with a given analysis type if one exists, else null
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
    * Gets how many Analysis objects are in the list
    * @return the number of Analysis objects in the list
    */
   public int size()
   {
      return analysises.size();
   }
/**
 * Gets a String representation of the AnalysisList.
 * @return String representation of the AnalysisList
 */
   public String toString()
   {
      return analysises.toString();
   }
   /**
    * Compares two Analysis objects
    * @param obj the object to compare with
    * @return true if the given object is equal to this analysis
    */
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
