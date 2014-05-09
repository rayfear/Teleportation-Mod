package tpmod.updates;

import java.util.ArrayList;
import java.util.List;

public class TextDecoder
{
	public char open = '{';
	public char close = '}';
	
	public String[] decode(String text, String... tags)
	{
		String[] values = new String[tags.length];
		
		int currentLetter = 0;
		
		for (int tag = 0; tag < tags.length; tag++)
		{
			String tagValue = null;
			
			while(text.charAt(currentLetter) != open)
			{
				currentLetter++;
			}
			
			while(text.charAt(currentLetter) != close)
			{
				if(text.charAt(currentLetter) != close && text.charAt(currentLetter) != open)
				{
					if(tagValue != null)
					{
						tagValue = tagValue + Character.toString(text.charAt(currentLetter));
					}
					else
					{
						tagValue = Character.toString(text.charAt(currentLetter));
					}
				}
				
				currentLetter++;
			}
			values[tag] = tagValue;
		}
		
		return values;
	}
	
	public List<String> stringArrayToList(String[] array)
	{
		List<String> listFromArray = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) 
		{
			listFromArray.add(array[i]);
		}
		return listFromArray;
	}
	
	public boolean contains(List<String> list, String contains)
	{
		for (String string : list) 
		{
			if(string.compareTo(contains) == 0)
			{
				return true;
			}
		}
		return false;
	}
}
