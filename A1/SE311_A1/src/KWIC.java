import java.util.ArrayList;
import java.util.Arrays;

public class KWIC {
	private ArrayList<String> key;
	private ArrayList<String> value;
	
	public KWIC()
	{
		this.key = new ArrayList<String>();
		this.value = new ArrayList<String>();
	}
	
	public int getKeySize()
	{
		return this.key.size();
	}
	
	public int getValueSize()
	{
		return this.value.size();
	}
	
	public void addKey(String newKey)
	{
		this.key.add(newKey);
	}
	
	public void addValue(String newValue)
	{
		this.addKey(newValue);
	}
	
	public String getKey(int i)
	{
		return this.key.get(i);
	}
	
	public String getValue(int i)
	{
		return this.value.get(i);
	}
	
	public ArrayList<String> getAllKeys()
	{
		return this.key;
	}
	
	public ArrayList<String> getAllValues()
	{
		return this.value;
	}
	
	public void setKeys(ArrayList<String> newKeys)
	{
		this.key =  newKeys;
	}
	
	public void setValues(ArrayList<String> newValues)
	{
		this.value = newValues;
	}
	
	public boolean isEven()
	{
		if (this.getKeySize() == this.getValueSize())
			return true;
		else
			return false;
	}

}
