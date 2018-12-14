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
   
   public void changeInformation(String name,String number, String initials)
   {
      WorkersList workers= getAllWorkers();

      for (int i = 0;i<workers.size();i++)
      {
         if (workers.get(i).getNumber().equals(number))
         {
            workers.get(i).setInitials(initials);
            workers.get(i).setName(name);
            
         }
      }
            
      saveWorkers(workers);
   }
   
   public void deleteWorker(String name, String number, String initials)
   {
      WorkersList workers= getAllWorkers();
      
      int i=workers.getIndex(name, number, initials);
      workers.get(i).setInitials("");
      workers.get(i).setName("");
      workers.get(i).setNumber("");
      
      workers.removeWorker(new Worker("","",""));
      
      saveWorkers(workers);
   }
   
   public void addWorker(String name, String number, String initials)
   {
      WorkersList workers= getAllWorkers();
      
      workers.addWorker(new Worker(name, number, initials));
      
      saveWorkers(workers);
   }
}
