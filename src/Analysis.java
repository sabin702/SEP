import java.io.Serializable;

public class Analysis implements Serializable
{
   private String analysisType;
   private String matrix;

   public Analysis(String analysisType, String matrix)
   {
      this.analysisType = analysisType;
      this.matrix = matrix;
   }

   public String getAnalysisType()
   {
      return analysisType;
   }

   public void setAnalysisType(String analysisType)
   {
      this.analysisType = analysisType;
   }

   public String getMatrix()
   {
      return matrix;
   }

   public void setMatrix(String matrix)
   {
      this.matrix = matrix;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Analysis))
      {
         return false;
      }
      Analysis other = (Analysis) obj;
      return analysisType.equals(other.analysisType) && matrix.equals(other.matrix);
   }

   public String toString()
   {
      return analysisType + " (" + matrix+")";
   }

}
