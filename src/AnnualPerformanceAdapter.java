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
   
   public WorkersList getAllWorkers()
   {
      WorkersList workers = new WorkersList();
      
      try
      {
         workers = (WorkersList)mifo.readObjectFromFile(fileName);
      }
      
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO error reading files");
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class not found");
      }
      
      return workers;
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

   public void changeAnnualPerformanceComment(String str, String name , String number, String initials)
   {
      AnnualPerformanceList annuals = getAllAnnualPerformance();
      
      for (int i =0;i<annuals.getSize();i++)
      {
         AnnualPerformance comment= annuals.getAnnualPerformance(i);
         if (comment.getWorker().getName().equals(name) 
               && comment.getWorker().getInitials().equals(initials)
               && comment.getWorker().getNumber().equals(number))
         { 
            comment.setComment(str);
         }
         else 
            comment.setComment(null);
      }
      

      saveAnnualPerformance(annuals);

   }

   public void deleteComment(String str, Worker worker)
   {
      AnnualPerformanceList annuals = getAllAnnualPerformance();
      AnnualPerformance comment = new AnnualPerformance(str, worker);

      for (int i = 0; i < annuals.getSize(); i++)
      {
         annuals.removeAnnualPerformance(comment);
      }

      saveAnnualPerformance(annuals);
   }
   
   public void addPerformance(Worker worker, String comment)
   {
      AnnualPerformanceList annuals= getAllAnnualPerformance();
      
      annuals.addAnnualPerformance(new AnnualPerformance(comment, worker));
      
      saveAnnualPerformance(annuals);
   }
}
