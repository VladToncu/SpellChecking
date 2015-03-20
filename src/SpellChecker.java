import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class SpellChecker {

	public static final int normalTerminationNoMistake = 0;
	public static final int normalTerminationMistake = 1;
	public static final int abnormalTermination = 2;

    // There is no constructor for this class.

  public static void main(String [] args) {
    if (args.length != 2) 
    {	
    	if(args.length!=1)
    	{
        System.err.println("Usage: java SpellChecker <file to be checked> <dictionary> or java SpellChecker <file to be checked>");
        System.exit(abnormalTermination);
    	}
    }
    FileReaderHandling handler;
    LinkedHashSet<String> dictionarySet;
    LinkedHashSet<String> filecheckSet;
    int lineNumber=0;
    boolean correctLine=true;
    ArrayList<String> wrongWords=new ArrayList<String>();
    ArrayList<String> wrongLine=new ArrayList<String>();
    ArrayList<Integer> wrongLineNumber=new ArrayList<Integer>();
    int exitCode=0;
    if(args.length==2)
    	handler=new FileReaderHandling(args[0], args[1]);
    else
    	handler=new FileReaderHandling(args[0], "/usr/share/dict/words");
    dictionarySet=handler.getWordsDictionary();
    filecheckSet=handler.getWordsFile();
    StringSpliter spliter;
    for (Iterator<String> it =filecheckSet.iterator(); it.hasNext();)
    {
    	correctLine=true;
    	String line=it.next();
    	spliter=new StringSpliter(line);
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
    		System.out.print(wrongLineNumber.get(i) + " ");
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
