import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the analyses file, making it easy to retrieve and store information.
 * @author Aleksander Bialik and Kresimir Bosnjak
 * @version 1.0
 */ 

public class AnalysisFileAdapter
{
   private MyFileIO mifo;
   private String fileName;

   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file 
    * where analyses will be saved and retrieved
    */
   
   public AnalysisFileAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

   /**
    * Uses the MyFileIO class to retrieve 
    * an AnalysisList object with all analyses.
    * @return an AnalysisList object with all stored analyses
    */
   
   public AnalysisList getAllAnalysis()
   {
      AnalysisList analysis = new AnalysisList();

      try
      {
         analysis = (AnalysisList) mifo.readObjectFromFile(fileName);
      }

      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO error reading files");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class not found");
      }

      return analysis;
   }

   /**
    * Use the MyFileIO class to save some analyses.
    * @param analysis the list of analyses that will be saved
    */
   
   public void saveAnalysis(AnalysisList analysis)
   {
      try
      {
         mifo.writeToFile(fileName, analysis);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file");
      }
   }
   
   /**
    * Uses the MyFileIO class to change 
    * the analysis type of an analysis with 
    * the given matrix type.
    * @param analysisType the analysis' new analysis type
    * @param matrix the analysis's matrix
    */

   public void changeAnalysisName(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();

      saveAnalysis(analyzes);

   }
   
   /**
    * Uses the MyFileIO class to change the matrix of an analysis with 
    * the given analysis type.
    * @param analysisType the analysis type of the analysis
    * @param matrix the analysis's new country
    */

   public void changeMatrixName(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();

      for (int i = 0; i < analyzes.size(); i++)
      {
         Analysis analysis = analyzes.get(i);

         if (analysis.getAnalysisType().equals(analysisType)
               && analysis.getMatrix().equals(matrix))
         {
            analysis.setMatrix(matrix);
         }
      }

      saveAnalysis(analyzes);
   }

   /**
    * Uses the MyFileIO class to delete an analysis with 
    * the given analysis type and matrix.
    * @param analysisType the analysis' type of the analysis
    * @param matrix the analysis' matrix
    */
   
   public void deleteAnalysis(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();
      
      int i=analyzes.getIndex(analysisType, matrix);
      analyzes.get(i).setAnalysisType("");
      analyzes.get(i).setMatrix("");
      
      analyzes.removeAnalysis(new Analysis("",""));
      
      saveAnalysis(analyzes);
   }
   
   /**
    * Uses the MyFileIO class to add an analysis
    * @param analysistype the analysis' type of the new analysis
    * @param matrix the analysis' matrix of the new analysis
    */
   
   public void addAnalysis(String analysistype, String matrix)
   {
      AnalysisList analysislist = getAllAnalysis();
      Analysis analysis = new Analysis(analysistype, matrix);
      
      analysislist.add(analysis);
      
      saveAnalysis(analysislist);
   }
}
