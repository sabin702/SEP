import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
   public static void main(String[] args)
   {
      WorkersList workers = new WorkersList();

      MyTextFileIO mtfio = new MyTextFileIO();
      String[] workerArray = null;
      try
      {
         workerArray = mtfio.readArrayFromFile("workers.txt");
                      
         for(int i = 0; i<workerArray.length; i++)
         {
            String temp = workerArray[i];
            String[] tempArr = temp.split(",");
            String name = tempArr[0];
            String number = tempArr[1];
            String initials = tempArr[2];

            workers.addWorker(new Worker(name,number,initials));
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File was not found, or could not be opened");
      }
     
      MyFileIO myfileio = new MyFileIO();
      
      try
      {
         myfileio.writeToFile("workers.bin", workers);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Error opening file ");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file ");
      }
      
      System.out.println("Done");
   }
}