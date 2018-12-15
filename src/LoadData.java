import java.io.FileNotFoundException;
import java.io.IOException;



public class LoadData
{
   public static void main(String[] args)
   {
      AnalysisList analysises = new AnalysisList();

      MyTextFileIO mtfio = new MyTextFileIO();
      String[] analysisArray = null;
      try
      {
         analysisArray = mtfio.readArrayFromFile("analysis.txt");
                      
         for(int i = 0; i<analysisArray.length; i++)
         {
            String temp = analysisArray[i];
            String[] tempArr = temp.split(",");
         
            String analysis = tempArr[0];
            String matrix = tempArr[1];

            analysises.add(new Analysis(analysis, matrix));
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File was not found, or could not be opened");
      }
     
      MyFileIO mfio = new MyFileIO();
      
      try
      {
         mfio.writeToFile("analysis.bin", analysises);
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
      
      ///WORKER
      WorkersList workers = new WorkersList();
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
      try
      {
         mfio.writeToFile("workers.bin", workers);
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