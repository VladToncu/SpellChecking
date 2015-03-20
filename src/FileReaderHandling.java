import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class FileReaderHandling {

	private String filepath;
	private String dictionarypath;
	private BufferedReader dictionaryReader;
	private BufferedReader fileReader;
	LinkedHashSet<String> dictionarySet=new LinkedHashSet<String>();
    LinkedHashSet<String> filecheckSet=new LinkedHashSet<String>();
    
    int exitCode=0;
    public FileReaderHandling(String filepath,String dictionarypath)
    {
    	this.filepath=filepath;
    	this.dictionarypath=dictionarypath;
    	try 
    	{
			dictionaryReader=new BufferedReader(new FileReader(this.dictionarypath));
		} 
    	catch (IOException e) 
    	{
            System.err.println("Error while reading the dictionary");
            System.err.println(e.getMessage());

            exitCode = 2;
        }
    	try 
    	{
			fileReader=new BufferedReader(new FileReader(this.filepath));
		} 
    	catch (IOException e) 
    	{
            System.err.println("Error while reading the dictionary");
            System.err.println(e.getMessage());

            exitCode = 2;
        }
    }
    public LinkedHashSet<String> getWordsDictionary()
    {
    	String line=null;
		try 
		{
			line = dictionaryReader.readLine();
		} 
		catch (IOException e) 
		{
			 System.err.println("Error while reading the dictionary");
	            System.err.println(e.getMessage());

	            exitCode = 2;
		}

	     while (line != null) 
	     {
	    	 dictionarySet.add(line.trim());
	    	 try 
	 		{
	 			line = dictionaryReader.readLine();
	 		} 
	    	 catch (IOException e) 
	 		{
	    		 try 
	    		 {
					dictionaryReader.close();
	    		 }
	    		 catch (IOException e1) 
	 	        {
	    			 System.err.println("Error while closing the dictionary");
		 	         System.err.println(e1.getMessage());
		 	         exitCode = 2;
				}
	 			 System.err.println("Error while reading the dictionary");
	 	         System.err.println(e.getMessage());
	 	         exitCode = 2;
	 		} 
	     }
	     try 
		 {
			dictionaryReader.close();
		 }
		 catch (IOException e1) 
	        {
			 System.err.println("Error while closing the dictionary");
 	         System.err.println(e1.getMessage());
 	         exitCode = 2;
		}
	     return this.dictionarySet;
    }
    public LinkedHashSet<String> getWordsFile()
    {
    	String line=null;
		try 
		{
			line = fileReader.readLine();
		} 
		catch (IOException e) 
		{
			 System.err.println("Error while reading the file");
	            System.err.println(e.getMessage());

	            exitCode = 2;
		}

	     while (line != null) 
	     {
	    	 filecheckSet.add(line.trim());
	    	 try 
	 		{
	 			line = fileReader.readLine();
	 		} 
	    	 catch (IOException e) 
	 		{
	    		 try 
	    		 {
					fileReader.close();
	    		 }
	    		 catch (IOException e1) 
	 	        {
	    			 System.err.println("Error while closing the file");
		 	         System.err.println(e1.getMessage());
		 	         exitCode = 2;
				}
	 			 System.err.println("Error while reading the file");
	 	         System.err.println(e.getMessage());
	 	         exitCode = 2;
	 		} 
	     }
	     try 
		 {
			fileReader.close();
		 }
		 catch (IOException e1) 
	        {
			 System.err.println("Error while closing the file");
 	         System.err.println(e1.getMessage());
 	         exitCode = 2;
		}
	     return this.filecheckSet;
    }
}
