import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the annual performance file, making it easy to retrieve and store information.
 * @author Lucian Rafa and Aleksander Bialik
 * @version 1.0
 */ 

public class AnnualPerformanceAdapter
{
   private MyFileIO mifo;
   private String fileName;

   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where employees' preferences will be saved and retrieved
    */
   
   public AnnualPerformanceAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

   /**
    * Uses the MyFileIO class to retrieve an AnnualPerformanceList object with all employees' preferences.
    * @return an AnnualPerformanceList object with all stored employees' preferences
    */
   
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
    * Use the MyFileIO class to save some employees' preferences.
    * @param annual the list of employees' preferences that will be saved
    */
   
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
   
   /**
    * Uses the MyFileIO class to change the preference of an employee with 
    * the given preference.
    * @param str the employee's new preference
    * @param name the employee's name
    * @param number the employee's working number
    * @param initials the employee's initials
    */

   public void changeAnnualPerformanceComment(String str, String name , String number, String initials)
   {
      AnnualPerformanceList annuals = getAllAnnualPerformance();
      AnnualPerformance annual= new AnnualPerformance(str, new Worker(name,number,initials));
      
      for (int i=0;i<annuals.getSize();i++)
      {
         if ((annual.getWorker().equals(annuals.getAnnualPerformance(i).getWorker()) 
               && !(annual.getComment().equals(annuals.getAnnualPerformance(i).getComment()))))
               {
                  annuals.getAnnualPerformance(i).setComment(annual.getComment());
               }
      }
      annuals.addAnnualPerformance(annual);
      
      saveAnnualPerformance(annuals);
   }
   
   /**
    * Uses the MyFileIO class to add an employee's preference
    * @param worker the worker the specified preference is going to be assigned to
    * @param comment the employee's preference
    */
   
   public void addPerformance(Worker worker, String comment)
   {
      AnnualPerformanceList annuals= getAllAnnualPerformance();
      
      annuals.addAnnualPerformance(new AnnualPerformance(comment, worker));
      
      saveAnnualPerformance(annuals);
   }
}
