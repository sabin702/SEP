import java.io.Serializable;

/**
 * A class representing an analysis with a analysis type and matrix
 * @author Aleksander Bialik and Sabin Sirbu
 * @version 1.0
 */ 
public class Analysis implements Serializable
{
   private String analysisType;
   private String matrix;
   /**
    * Two-argument constructor.
    * @param analysisType the analysis' name
    * @param matrix the analysis' matrix
    */
   public Analysis(String analysisType, String matrix)
   {
      this.analysisType = analysisType;
      this.matrix = matrix;
   }
/**
 * Gets the analysis type
 * @return the analysis type
 */
   public String getAnalysisType()
   {
      return analysisType;
   }
/**
 * Sets the type of the analysis
 * @param analysisType what the analysis type will be set to
 */
   public void setAnalysisType(String analysisType)
   {
      this.analysisType = analysisType;
   }
   /**
    * Gets the matrix
    * @return the analysis' matrix
    */
   public String getMatrix()
   {
      return matrix;
   }
   
   /**
    * Sets the analysis' matrix
    * @param matrix what the analysis' matrix will be set to
    */
   public void setMatrix(String matrix)
   {
      this.matrix = matrix;
   }
   /**
    * Compares analysis type and matrix of two analysis.
    * @param obj the object to compare with
    * @return true if the given object is equal to this analysis
    */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Analysis))
      {
         return false;
      }
      Analysis other = (Analysis) obj;
      return analysisType.equals(other.analysisType) && matrix.equals(other.matrix);
   }
   /**
    * Returns a string representation of the analysis.
    * @return a string representation of the analysis in the format: "analysisType matrix"
    */
   public String toString()
   {
      return analysisType + " (" + matrix+")";
   }

}
