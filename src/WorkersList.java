import java.io.Serializable;
import java.util.ArrayList;
/**
 * A class containing a list of Worker objects
 * @author Kresimir Bosnjak
 * @version 1.0
 */ 
public class WorkersList implements Serializable
{
   private ArrayList<Worker> workers;
   /**
    * No-argument constructor initializing the AnalysisList.
    */
   public WorkersList()
   {
      workers= new ArrayList<Worker>();
   }
   /**
    * Adds the Worker to the list
    * @param worker the worker to add to the list
    */
   public void addWorker(Worker worker)
   {
      workers.add(worker);
   }
   /**
    * Removes the Worker from the list
    * @param worker the worker to delete from the list
    */
   public void removeWorker(Worker worker)
   {
      workers.remove(worker);
   }
   /**
    * Gets the Worker with given name, number and initials
    * @param name worker's name
    * @param number worker's number
    * @param intials worker's initials
    * @return the Worker with given name, number and initials if one does exist, otherwise returns null
    */
   public Worker get(String name,String number,String intials)
   {
      for (int i=0; i<workers.size();i++)
      {
         Worker temp= workers.get(i);
         
         if (temp.getName().equals(name) && temp.getNumber().equals(number) && temp.getInitials().equals(intials))
         {
            return workers.get(i);
         }
      }
      return null;
   }
   /**
    * Gets how many Worker objects are in the list
    * @return the number of Worker objects in the list
    */
   public int size()
   {
      return workers.size();
   }
   /**
    * Gets the index of a given Worker object
    * @param name the name of the Worker object
    * @param number the number of the Worker object
    * @param initials the initials of the Worker object
    * @return the index of a given Worker object
    */
   public int getIndex(String name, String number, String initials)
   {
      for(int i = 0; i<workers.size(); i++)
      {
         Worker temp = workers.get(i);
         
         if(temp.getName().equals(name) && temp.getNumber().equals(number) && temp.getInitials().equals(initials))
         {
            return i;
         }
      }  
      return -1;
   }
   /**
    * Gets the Worker on the given position
    * @param index position in the list
    * @return the Worker on the given position
    */
   public Worker get(int index)
   {
      if(index<workers.size())
      {
         return workers.get(index);
      }
      else
      {
         return null;
      }
   }
   /**
    * Replaces the Worker object at index with worker.
    * @param worker the worker to replace with
    * @param index position in the list
    */
   public void set(Worker worker, int index)
   {
      workers.set(index, worker);
   }
   /**
    * Gets a String representation of the WorkersList.
    * @return String representation of the WorkersList
    */
   public String toString()
   {
      String returnString="";
      
      for (int i=0;i <workers.size();i++)
      {
         Worker temp = workers.get(i);
         
         returnString+= temp+"\n";
      }
      return returnString;
   }
   /**
    * Compares two Worker objects
    * @param obj the object to compare with
    * @return true if the given object is equal to this worker
    */
   public boolean equals(Object obj)
   {
      WorkersList other=(WorkersList)obj;
      
      if (other.workers.size()!=this.workers.size())
      {
         return false;
      }
      else
      {
         for (int i=0;i<this.workers.size();i++)
            {
               if ((!other.workers.get(i).equals(this.workers.get(i))))
               {
                  return false;
               }
            }
         return true;
      }
   }
}
