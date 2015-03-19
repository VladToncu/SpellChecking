package checking;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class SpellCheckerMain {

	public static final int normalTerminationNoMistake = 0;
	public static final int normalTerminationMistake = 1;
	public static final int abnormalTermination = 2;

    // There is no constructor for this class.

  public static void main(String [] args) {
    if (args.length != 2 || args.length !=1) {
        System.err.println("Usage: java SpellChecker <file to be checked> <dictionary> or java SpellChecker <file to be checked>");
        System.exit(abnormalTermination);
    }
    int exitCode= normalTerminationNoMistake;
    FileInputStream filecheck=null;
    FileInputStream dictionary=null;
    LinkedHashSet<String> dictionarySet=new LinkedHashSet<String>();
    LinkedHashSet<String> filecheckSet=new LinkedHashSet<String>();
    int lineNumber=0;
    boolean correctLine=true;
    ArrayList<String> wrongWords=new ArrayList<String>();
    ArrayList<String> wrongLine=new ArrayList<String>();
    ArrayList<Integer> wrongLineNumber=new ArrayList<Integer>();
    try {
    	
    	if(args.length == 2)
    	{
    		filecheck=new FileInputStream(args[0]);
    		dictionary=new FileInputStream(args[1]);
    		BufferedReader dict = 
                    new BufferedReader(
                          new InputStreamReader(
                                  dictionary));
    		BufferedReader file = 
                    new BufferedReader(
                          new InputStreamReader(
                                  filecheck));
    		
    		String line = dict.readLine();

    	     while (line != null) 
    	     {
    	    	 dictionarySet.add(line);
    	         line = dict.readLine(); 
    	     }
    	          
    	    line = file.readLine();

    	    while (line != null) 
    	    {
    	    	 filecheckSet.add(line);
    	    	 line = file.readLine();
    	    }
    	     }
    	  	else
    	{
    	  		filecheck=new FileInputStream(args[0]);
        		dictionary=new FileInputStream("/usr/share/dict/words");
        		BufferedReader dict = 
                        new BufferedReader(
                              new InputStreamReader(
                                      dictionary));
        		BufferedReader file = 
                        new BufferedReader(
                              new InputStreamReader(
                                      filecheck));
        		
        		String line = dict.readLine();

        	     while (line != null) 
        	     {
        	    	 dictionarySet.add(line);
        	         line = dict.readLine(); 
        	     }
        	          
        	    line = file.readLine();

        	    while (line != null) 
        	    {
        	    	 filecheckSet.add(line);
        	    	 line = file.readLine();
        	    }
    	}
        
       }
     catch (IOException e) 
    	{
          System.err.println("IO error " + args[0] + args[1]);
          System.err.println(e.getMessage());
          
          exitCode = abnormalTermination;
    	}
    finally {
        try {
        	dictionary.close();
        	filecheck.close();
        }
        catch (IOException e) {
            System.err.println("Error while closing files");
            System.err.println(e.getMessage());

            exitCode = abnormalTermination; // We've changed our mind.
        }
    }
    for (Iterator<String> it =filecheckSet.iterator(); it.hasNext(); )
    {
    	correctLine=true;
    	String line=it.next();
    	StringSpliter spliter=new StringSpliter(line);
    	WordFinder find;
    	for(int i=0;i<spliter.getWords().size();i++)
    	{
    		find=new WordFinder(spliter.getWords().get(i),dictionarySet);
    		if(!find.isValid())
    		{
    			wrongWords.add(spliter.getWords().get(i));
    			correctLine=false;
    		}
    	}
    	if(!correctLine)
    	{
    		wrongLine.add(line);
    		wrongLineNumber.add(lineNumber);
    	}
    	lineNumber++;
    }
    if(wrongLineNumber.size()==0)
    {
    	exitCode=normalTerminationNoMistake;
    }
    else
    {
    	for(int i=0;i<wrongLineNumber.size();i++)
    		System.out.print(wrongLineNumber + " ");
    	System.out.println();
    	for(int i=0;i<wrongLine.size();i++)
    		System.out.print(wrongLine.get(i) + " ");
    	System.out.println();
    	for(int i=0;i<wrongWords.size();i++)
    		System.out.print(wrongWords.get(i) + " ");
    	exitCode=normalTerminationMistake;
    }
    System.exit(exitCode);
  }
}
