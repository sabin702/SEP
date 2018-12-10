import java.util.ArrayList;

public class WorkersList
{
   private ArrayList<Worker> workers;
   
   public WorkersList()
   {
      workers= new ArrayList<Worker>();
   }
   
   public void addWorker(Worker worker)
   {
      workers.add(worker);
   }
   
   public void removeWorker(Worker worker)
   {
      workers.remove(worker);
   }
   
   public void sortByName()
   {
      
   }
   
   public String toString()
   {
      return workers.toString();
   }
   
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
