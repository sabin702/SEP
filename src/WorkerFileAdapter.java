import  java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the worker file, making it easy to retrieve and store information.
 * @author Kresimir Bosnjak
 * @version 1.0
 */ 


public class WorkerFileAdapter
{
   private MyFileIO mifo;
   private String fileName;
   
   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where workers will be saved and retrieved
    */
   
   public WorkerFileAdapter(String fileName)
   {
      mifo=new MyFileIO();
      this.fileName=fileName;
   }
   
   /**
    * Uses the MyFileIO class to retrieve an WorkersList object with all workers.
    * @return an WorkersList object with all stored workers
    */
   
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
   
   /**
    * Use the MyFileIO class to save some workers.
    * @param workers the list of workers that will be saved
    */
   
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
   
   /**
    * Uses the MyFileIO class to change the worke's name and intials with 
    * the given working number.
    * @param name the employee's name
    * @param number the employee's working number
    * @param initials the employee's initials
    */

   
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
   
   /**
    * Uses the MyFileIO class to delete a worker with 
    * the given name, initials and working number.
    * @param name the employee's name
    * @param number the employee's working number
    * @param initials the employee's initials
    */
   
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
   
   /**
    * Uses the MyFileIO class to add a worker
    * @param name the employee's name
    * @param number the employee's working number
    * @param initials the employee's initials
    */
   
   public void addWorker(String name, String number, String initials)
   {
      WorkersList workers= getAllWorkers();
      
      workers.addWorker(new Worker(name, number, initials));
      
      saveWorkers(workers);
   }
}
