
import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Training objects
 * @author Aleksander Bialik, Kresimir Bosnjak
 * @version 1.0
 */ 
public class TrainingList implements Serializable

{
   ArrayList<Training> trainings;
   /**
    * No-argument constructor initializing the TrainingList.
    */
   public TrainingList()
   {
      trainings = new ArrayList<Training>();
   }
/**
 * Adds a training to the list
 * @param train training's training status
 * @param analysis training's analysis
 * @param worker training's worker
 */
   public void addTraining(String train, Analysis analysis, Worker worker)
   {
      trainings.add(new Training(train, analysis, worker));
   }
   /**
    * Gets how many Training objects are in the list
    * @return the number of Training objects in the list
    */
   public int getSize()
   {
      return trainings.size();
   }
   /**
    * Gets the Training object from position index from the list. 
    * @param index the position in the list of the Training object
    * @return the Training object at position index
    */
   public Training get(int index)
   {
      return trainings.get(index);
   }
   /**
    * Gets the Training object from position index from the list. 
    * @param index the position in the list of the Training object
    * @return the Training object at position index
    */
   public Training getTraining(int index)
   {
      return trainings.get(index);
   }
   /**
    * Gets the index of a given Training object
    * @param training worker, analysis and training status in the Training object
    * @return the index of a given Training object
    */
   public int getIndex(Training training)
   {
      for (int i = 0; i < trainings.size(); i++)
      {
         Training temp = trainings.get(i);

         if (temp.getAnalysis().getAnalysisType()
               .equals(training.getAnalysis().getAnalysisType())
               && temp.getAnalysis().getMatrix()
                     .equals(training.getAnalysis().getMatrix())
               && temp.getTrainingStatus().equals(training.getTrainingStatus())
               && temp.getWorker().getName()
                     .equals(training.getWorker().getName())
               && temp.getWorker().getInitials()
                     .equals(training.getWorker().getInitials())
               && temp.getWorker().getNumber()
                     .equals(training.getWorker().getNumber()))
            ;
         {
            return i;
         }
      }
      return -1;
   }
   /**
    * Removes the Training  from the list
    * @param train the training to delete from the list
    */
   public void removeTraining(Training train)
   {
      trainings.remove(train);
   }
   /**
    * Removes the Training  from the given position in the list
    * @param i the position of the Training in the list
    */
   public void removeIndex(int i)
   {
      trainings.remove(i);
   }
   /**
    * Gets a String representation of the TrainingList.
    * @return String representation of the TrainingList
    */
   public String toString()
   {
      return trainings.toString();
   }
   /**
    * Compares two Training objects
    * @param obj the object to compare with
    * @return true if the given object is equal to this training
    */
   public boolean equals(Object obj)
   {
      TrainingList other = (TrainingList) obj;

      if (other.trainings.size() != this.trainings.size())
      {
         return false;
      }
      else
      {
         for (int i = 0; i < this.trainings.size(); i++)
         {
            if ((!other.trainings.get(i).equals(this.trainings.get(i))))
            {
               return false;
            }
         }
         return true;
      }
   }

}
