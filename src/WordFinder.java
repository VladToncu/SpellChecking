import java.util.Iterator;
import java.util.LinkedHashSet;

public class WordFinder {

	private boolean isValid=false;
	LinkedHashSet<String> dictionaryLowerCase=new LinkedHashSet<String>();
	public WordFinder(String s, LinkedHashSet<String> dictionary)
	{
		for (Iterator<String> it =dictionary.iterator(); it.hasNext(); )
		{
			dictionaryLowerCase.add(it.next().toLowerCase());
		}
		if(dictionaryLowerCase.contains(s.toLowerCase()))
		{
			isValid=true;
		}
	}
	public boolean isValid()
	{
		return isValid;
	}
}
