
import java.io.Serializable;
import java.util.ArrayList;

public class TrainingList implements Serializable

{
   ArrayList<Training> trainings;

   public TrainingList()
   {
      trainings = new ArrayList<Training>();
   }

   public void addTraining(String train, Analysis analysis, Worker worker)
   {
      trainings.add(new Training(train, analysis, worker));
   }

   public int getSize()
   {
      return trainings.size();
   }

   public Training get(int index)
   {
      return trainings.get(index);
   }

   public Training getTraining(int index)
   {
      return trainings.get(index);
   }

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

   public void removeTraining(Training train)
   {
      trainings.remove(train);
   }

   public void removeIndex(int i)
   {
      trainings.remove(i);
   }

   public void sortByName()
   {

   }

   public String toString()
   {
      return trainings.toString();
   }

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
