import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the training file, making it easy to retrieve and store information.
 * @author Lucian Rafa and Aleksander Bialik
 * @version 1.0
 */ 

public class TrainingFileAdapter
{
   private MyFileIO mifo;
   private String fileName;

   /**
    * 1-argument constructor setting the file name.
    * @param fileName the name and path of the file where trainings will be saved and retrieved
    */
   
   public TrainingFileAdapter(String fileName)
   {
      mifo = new MyFileIO();
      this.fileName = fileName;
   }

   /**
    * Uses the MyFileIO class to retrieve a TrainingList object with all trainings.
    * @return an TrainingList object with all stored trainings
    */
   
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

   /**
    * Use the MyFileIO class to save some trainings.
    * @param trainings the list of trainings that will be saved
    */
   
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

   /**
    * Uses the MyFileIO class to add a training
    * @param trainingStatus the training status of 
    * an employee for a specific analysis
    * @param analysis the analysis in 
    * which the employee is going to be trained on
    * @param worker the employee assigned for the training
    */

   public void addTrainings(String trainingStatus, Analysis analysis,
         Worker worker)
   {
      TrainingList trainings = getAllTrainings();
      trainings.addTraining(trainingStatus, analysis, worker);
      saveTraining(trainings);
   }
   
   /**
    * Uses the MyFileIO class to delete a selected training .
    * @param trainingStatus the training status of
    * the selected training
    * @param analysis the analysis in which the employee was trained
    * @param worker the employee that was trained in a specific analysis
    */

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
