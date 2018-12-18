import java.io.Serializable;
/**
 * A class representing a worker with a name, number and initials.
 * @author Kresimir Bosnjak
 * @version 1.0
 */ 
public class Worker implements Serializable
{
   private String name;
   private String number;
   private String initials;
   /**
    * Three-argument constructor
    * @param name the worker's name
    * @param number the worker's number
    * @param initials the worker's initials
    */
   public Worker(String name, String number, String initials)
   {
      this.setName(name);
      this.setNumber(number);
      this.setInitials(initials);
   }
/**
 * Gets the worker's name
 * @return the worker's name
 */
   public String getName()
   {
      return name;
   }
/**
 * Sets the worker's name
 * @param name what the worker's name will be set to
 */
   public void setName(String name)
   {
      this.name = name;
   }
/**
 * Gets the worker's number
 * @return the worker's number
 */
   public String getNumber()
   {
      return number;
   }
/**
 * Sets the worker's number
 * @param number what the worker's name will be set to
 */
   public void setNumber(String number)
   {
      this.number = number;
   }
/**
 * Gets the worker's initials
 * @return the worker's initials
 */
   public String getInitials()
   {
      return initials;
   }
/**
 * Sets the worker's initials
 * @param initials what the worker's initials will be set to
 */
   public void setInitials(String initials)
   {
      this.initials = initials;
   }
   /**
    * Returns a string representation of the worker.
    * @return a string representation of the worker in the format: "name, number, initials"
    */
   public String toString()
   {
      return this.name +", "+ this.number +", "+ this.initials;
   }
   /**
    * Compares name, number and initials of two workers.
    * @param obj the object to compare with
    * @return true if the given object is equal to this worker
    */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Worker))
         return false;
      Worker other=(Worker)obj;
      if (other.name.equals(this.name) && other.number.equals(this.number)
            && other.initials.equals(this.initials))
         return true;
      else 
         return false;
   }
   
   
}
