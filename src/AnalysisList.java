import java.util.ArrayList;


public class AnalysisList
{

   ArrayList<Analysis> analysis;

   public AnalysisList()
   {
      analysis = new ArrayList<Analysis>();
   }

   public void addAnalysis(Analysis index)
   {
      analysis.add(index);
   }

   public void removeAnalysis(Analysis index)
   {
      analysis.remove(index);
   }

  

   public String toString()
   {
      return analysis.toString();
   }

   public boolean equals(Object obj)
   {
      AnalysisList other = (AnalysisList) obj;

      if (other.analysis.size() != this.analysis.size())
      {
         return false;
      }
      else
      {
         for (int i = 0; i < this.analysis.size(); i++)
         {
            if ((!other.analysis.get(i).equals(this.analysis.get(i))))
            {
               return false;
            }
         }
         return true;
      }
   }

}
