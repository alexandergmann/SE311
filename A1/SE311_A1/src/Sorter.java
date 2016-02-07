import java.util.ArrayList;
import java.util.Arrays;

public class Sorter {
	private ArrayList<String> tempKeys;
	private ArrayList<String> tempValues;
	private String[] tempArray;
	
	public Sorter()
	{
		
	}
	
	public KWIC alphabetizeKWIC(KWIC kwicToSort)
	{
		this.tempKeys = kwicToSort.getAllKeys();
		this.tempArray = (String[]) tempKeys.toArray();
		this.tempValues = kwicToSort.getAllValues();
		int keysSize = tempKeys.size();		
		Arrays.sort(this.tempArray);
		
		for(int i=0; i < keysSize; i++)
		{
		}
		return kwicToSort;
	}
	
	public int compareKeys(String key1, String key2)
	{
		return key1.compareTo(key2);
	}
}
