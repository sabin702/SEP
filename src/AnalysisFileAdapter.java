import java.io.FileNotFoundException;
import java.io.IOException;

public class AnalysisFileAdapter
{
   private MyFileIO mifo;
   private String fileName;

   public AnalysisFileAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

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

   public void changeAnalysisName(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();

      for (int i = 0; i < analyzes.getSize(); i++)
      {
         Analysis analysis = analyzes.getAnalysis(i);

         if (analysis.getAnalysisType().equals(analysisType)
               && analysis.getMatrix().equals(matrix))
         {
            analysis.setAnalysisType(analysisType);
         }
      }

      saveAnalysis(analyzes);

   }

   public void changeMatrixName(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();

      for (int i = 0; i < analyzes.getSize(); i++)
      {
         Analysis analysis = analyzes.getAnalysis(i);

         if (analysis.getAnalysisType().equals(analysisType)
               && analysis.getMatrix().equals(matrix))
         {
            analysis.setMatrix(matrix);
         }
      }

      saveAnalysis(analyzes);
   }

   public void deleteAnalysis(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();
      Analysis analysis = new Analysis(analysisType, matrix);

      for (int i = 0; i < analyzes.getSize(); i++)
      {
         analyzes.removeAnalysis(analysis);
      }

      saveAnalysis(analyzes);
   }
}
