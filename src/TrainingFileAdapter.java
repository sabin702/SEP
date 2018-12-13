import java.io.FileNotFoundException;
import java.io.IOException;

public class TrainingFileAdapter
{
   private MyFileIO mifo;
   private String fileName;

   public TrainingFileAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

   public TrainingList getAllTrainings()
   {
      TrainingList training = new TrainingList();

      try
      {
         training = (TrainingList) mifo.readObjectFromFile(fileName);
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

      return training;
   }

   public void saveTraining(TrainingList training)
   {
      try
      {
         mifo.writeToFile(fileName, training);
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
   public void changeTraining(String str, Analysis analysis, Worker worker)
   {
      TrainingList trainings = getAllTrainings();

      for (int i = 0; i < trainings.getSize(); i++)
      {
         Training training = trainings.getTraining(i);

         if (training.getTrainingStatus().equals(str)
               && training.getAnalysis().equals(analysis) && training.getWorker().equals(worker))
         {
            training.setTrainingStatus(str);
         }
      }

      saveTraining(trainings);
   }
   
   
   public void deleteTraining(String trainingStatus, Analysis analysis, Worker worker)
   {
      TrainingList trainings = getAllTrainings();
      Training training = new Training(trainingStatus, analysis, worker);
      
      for (int i=0;i<trainings.getSize();i++)
      {
         trainings.removeTraining(training);
      }
      
      saveTraining(trainings);
   }
   
   
}
