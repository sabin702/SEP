import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A class responsible for reading and writing text files.
 * @author Group 3
 * @version 1.0 
 */
public class MyTextFileIO
{
   /** 
    * Writes the given string to a file with the given file name.
    * @param fileName the name and path of the file to write to
    * @param str the text string to write to the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public void writeToFile(String fileName, String str) throws FileNotFoundException
   {
      write(fileName, str, false);  
   }
  
   /**
    * Appends the given string to a file with the given file name.
    * @param fileName the name and path of the file to write to
    * @param str the text string to append to the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public void appendToFile(String fileName, String str) throws FileNotFoundException
   {
      write(fileName, str, true);    
   }
   
   // writeToFile and appendToFile are almost identical - only the boolean in the constructor
   // of the FileOutputStream differs. So I made this private method that both methods call
   private void write(String fileName, String str, boolean append) throws FileNotFoundException
   {
      PrintWriter writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
         writeToFile = new PrintWriter(fileOutStream);
         writeToFile.println(str);
      }
      finally
      {
         if (writeToFile != null)
         {
            writeToFile.close();
         }
      }
   }

   /**
    * Writes the strings in the given array to a file with the given file name.
    * @param fileName the name and path of the file to write to
    * @param strs the String array to write to the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public void writeToFile(String fileName, String[] strs) throws FileNotFoundException
   {
      write(fileName, strs, false);
   }

   /**
    * Appends the strings in the given array to a file with the given file name.
    * @param fileName the name and path of the file to write to
    * @param strs the String array to append to the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public void appendToFile(String fileName, String[] strs) throws FileNotFoundException
   {
      write(fileName, strs, true);
   }

   // Again the writeToFile and appendToFile with arrays are almost identical. 
   // So I made this private method that both methods call
   private void write(String fileName, String[] strs, boolean append) throws FileNotFoundException
   {
      PrintWriter writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
         writeToFile = new PrintWriter(fileOutStream);

         for (int i = 0; i < strs.length; i++)
         {
            writeToFile.println(strs[i]);
         }
      }
      finally
      {
         if (writeToFile != null)
         {
            writeToFile.close();
         }
      }
   }
  
   /**
    * Reads the first line from the file with the given file name.
    * @param fileName the name and path of the file that is read
    * @return a String containing the line read from the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public String readStringFromFile(String fileName) throws FileNotFoundException
   {
      Scanner readFromFile = null;
      String str = "";

      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new Scanner(fileInStream);
         str = readFromFile.nextLine();
      }
      finally
      {
         if (readFromFile != null)
         {
            readFromFile.close();
         }
      }
      return str;
   }

   /**
    * Reads all lines from the file with the given file name.
    * @param fileName the name and path of the file that is read
    * @return a String array containing all lines read from the file
    * @throws FileNotFoundException if the file with fileName is not found
    */
   public String[] readArrayFromFile(String fileName) throws FileNotFoundException
   {
      Scanner readFromFile = null;
      ArrayList<String> strs = new ArrayList<String>();
  
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new Scanner(fileInStream);

         while (readFromFile.hasNext())
         {
            strs.add(readFromFile.nextLine());
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            readFromFile.close();
         }
      }

      String[] strsArray = new String[strs.size()];
      return strs.toArray(strsArray);
   }
}
