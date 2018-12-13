import java.io.FileNotFoundException;
import java.io.IOException;

public class AnnualPerformanceAdapter
{
   private MyFileIO mifo;
   private String fileName;

   public AnnualPerformanceAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

   public AnnualPerformanceList getAllAnnualPerformance()
   {
      AnnualPerformanceList annual = new AnnualPerformanceList();

      try
      {
         annual = (AnnualPerformanceList) mifo.readObjectFromFile(fileName);
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

      return annual;
   }

   public void saveAnnualPerformance(AnnualPerformanceList annual)
   {
      try
      {
         mifo.writeToFile(fileName, annual);
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

   public void changeAnnualPerformanceComment(String str, Worker worker)
   {
      AnnualPerformanceList annuals = getAllAnnualPerformance();

      for (int i = 0; i < annuals.getSize(); i++)
      {
         AnnualPerformance comment = annuals.getAnnualPerformance(i);

         if (comment.getComment().equals(str)
               && comment.getWorker().equals(worker))
         {
            comment.setComment(str);
         }
      }

      saveAnnualPerformance(annuals);

   }

   public void deleteTraining(String str, Worker worker)
   {
      AnnualPerformanceList annuals = getAllAnnualPerformance();
      AnnualPerformance comment = new AnnualPerformance(str, worker);

      for (int i = 0; i < annuals.getSize(); i++)
      {
         annuals.removeAnnualPerformance(comment);
      }

      saveAnnualPerformance(annuals);
   }
}
