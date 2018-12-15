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
      
      
    ///TRAINING
      TrainingList trainings = new TrainingList();
      String[] trainingArray = null;
      try
      {
         trainingArray = mtfio.readArrayFromFile("training.txt");
                      
         for(int i = 0; i<trainingArray.length; i++)
         {
            String temp = trainingArray[i];
            String[] tempArr = temp.split(",");
         
            String trainingStatus = tempArr[0];
            String analysis = tempArr[1];
            String matrix =  tempArr[2];
            String name = tempArr[3];
            String number = tempArr[4];
            String initials = tempArr[5];
            
            //workers.addWorker(new Worker(name,number,initials));
            trainings.addTraining(trainingStatus, new Analysis(analysis, matrix), new Worker(name, number, initials));
         }
         
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File was not found, or could not be opened");
      }     
      try
      {
         mfio.writeToFile("training.bin", trainings);
      }
      
      catch (FileNotFoundException e)
      {
         System.out.println("Error opening file ");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file ");
         e.printStackTrace();
      }
      
      System.out.println("Done");
      System.out.println(trainings);
      
      ///ANNUALPREFRENCE
      AnnualPerformanceList annuals = new AnnualPerformanceList();
      String[] annualperformance = null;
      try
      {
         annualperformance = mtfio.readArrayFromFile("annualPerformance.txt");
                      
         for(int i = 0; i<annualperformance.length; i++)
         {
            String temp = annualperformance[i];
            String[] tempArr = temp.split(",");
         
            String name = tempArr[0];
            String number = tempArr[1];
            String initials = tempArr[2];
            
            String prefrence="";
                  
            annuals.addAnnualPerformance(new AnnualPerformance(prefrence, new Worker(name,number,initials)));
         }
         
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File was not found, or could not be opened");
      }     
      try
      {
         mfio.writeToFile("annuals.bin", annuals);
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
