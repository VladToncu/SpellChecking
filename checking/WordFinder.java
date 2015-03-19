package checking;

import java.util.LinkedHashSet;

public class WordFinder {

	private boolean isValid=false;
	public WordFinder(String s, LinkedHashSet<String> dictionary)
	{
		if(dictionary.contains(s))
		{
			isValid=true;
		}
	}
	public boolean isValid()
	{
		return isValid;
	}
}
