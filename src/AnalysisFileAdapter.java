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

      

      saveAnalysis(analyzes);

   }

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

   public void deleteAnalysis(String analysisType, String matrix)
   {
      AnalysisList analyzes = getAllAnalysis();
      
      int i=analyzes.getIndex(analysisType, matrix);
      analyzes.get(i).setAnalysisType("");
      analyzes.get(i).setMatrix("");
      
      analyzes.removeAnalysis(new Analysis("",""));
      
      saveAnalysis(analyzes);
   }
   
   public void addAnalysis(String analysistype, String matrix)
   {
      AnalysisList analysislist = getAllAnalysis();
      Analysis analysis = new Analysis(analysistype, matrix);
      
      analysislist.add(analysis);
      
      saveAnalysis(analysislist);
   }
}
