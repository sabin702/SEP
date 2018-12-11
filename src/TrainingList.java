import java.util.ArrayList;

public class TrainingList
{
   ArrayList<Training> training;

   public TrainingList()
   {
      training = new ArrayList<Training>();
   }

   public void addTraining(Training index)
   {
      training.add(index);
   }

   public void removeTraining(Training index)
   {
      training.remove(index);
   }

   public void sortByName()
   {

   }

   public String toString()
   {
      return training.toString();
   }

   public boolean equals(Object obj)
   {
      TrainingList other = (TrainingList) obj;

      if (other.training.size() != this.training.size())
      {
         return false;
      }
      else
      {
         for (int i = 0; i < this.training.size(); i++)
         {
            if ((!other.training.get(i).equals(this.training.get(i))))
            {
               return false;
            }
         }
         return true;
      }
   }

}
