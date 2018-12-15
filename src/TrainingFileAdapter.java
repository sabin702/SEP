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
      TrainingList trainings = new TrainingList();

      try
      {
         trainings = (TrainingList) mifo.readObjectFromFile(fileName);
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

      return trainings;
   }

   public void saveTraining(TrainingList trainings)
   {
      try
      {
         mifo.writeToFile(fileName, trainings);
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

  

   public void addTrainings(String trainingStatus, Analysis analysis,
         Worker worker)
   {
      TrainingList trainings = getAllTrainings();
      trainings.addTraining(trainingStatus, analysis, worker);
      saveTraining(trainings);
   }

   public void deleteTraining(String trainingStatus, Analysis analysis,
         Worker worker)
   {
      TrainingList trainings = getAllTrainings();

      int i = trainings
            .getIndex(new Training(trainingStatus, analysis, worker));
      trainings.removeIndex(i);

      saveTraining(trainings);
   }

}
