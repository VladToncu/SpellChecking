package checking;

import java.util.LinkedList;

public class StringSpliter {

	private LinkedList<String> words;
	public StringSpliter(String s)
	{
		words=new LinkedList<String>();
		char[] word=new char[s.length()];
		int counter=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)>='a' && s.charAt(i)<='z' || s.charAt(i)>='A' && s.charAt(i)<='Z')
			{
				word[counter]=s.charAt(i);
				counter++;
			}
			else
			{
				if(counter>0)
				{
					String wordString=new String(word);
					words.add(wordString);
					word=new char[s.length()];
				}
				counter=0;
			}
		}
	}
	public LinkedList<String> getWords()
	{
		return words;
	}
}
