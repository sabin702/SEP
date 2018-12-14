import java.io.Serializable;
import java.util.ArrayList;

public class WorkersList implements Serializable
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
   
   public int size()
   {
      return workers.size();
   }
   
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
   
   public void set(Worker worker, int index)
   {
      workers.set(index, worker);
   }
   
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
