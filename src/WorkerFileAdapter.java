import  java.io.FileNotFoundException;
import java.io.IOException;


public class WorkerFileAdapter
{
   private MyFileIO mifo;
   private String fileName;
   
   public WorkerFileAdapter(String fileName)
   {
      mifo=new MyFileIO();
      this.fileName=fileName;
   }
   
   public WorkersList getAllWorkers()
   {
      WorkersList workers=new WorkersList();
      
      try
      {
         workers= (WorkersList)mifo.readObjectFromFile(fileName);
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
      
      return workers;
   }
   
   public void saveWorkers(WorkersList workers)
   {
      try
      {
         mifo.writeToFile(fileName, workers);
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
   
   public void changeWorkerName() {
      
   }
   
}
