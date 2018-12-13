import java.io.Serializable;

public class Worker implements Serializable
{
   private String name;
   private int number;
   private String initials;
   
   public Worker(String name, int number, String initials)
   {
      this.setName(name);
      this.setNumber(number);
      this.setInitials(initials);
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getNumber()
   {
      return number;
   }

   public void setNumber(int number)
   {
      this.number = number;
   }

   public String getInitials()
   {
      return initials;
   }

   public void setInitials(String initials)
   {
      this.initials = initials;
   }
   
   public String toString()
   {
      return this.name + this.number + this.initials;
   }
   
   public boolean equals(Object obj)
   {
      Worker other=(Worker)obj;
      if (other.name.equals(this.name) && other.number==this.number
            && other.initials.equals(this.initials))
         return true;
      else 
         return false;
   }
   
   public Worker copy() {
      return new Worker(name, number, initials);
   }
}
