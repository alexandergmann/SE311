// class like a dictionary that holds a key and value along with the line they are from
public class KWICMap implements Comparable<KWICMap> {
	private String key;
	private String value;
	private String line;
	
	public KWICMap() {
		
	}
	
	public KWICMap(String key, String value, String line) {
		this.key = key;
		this.value = value;
		this.line = line;
	}
	
	public void setKey(String newKey) {
		this.key = newKey;
	}
	
	public void setValue(String newValue) {
		this.value = newValue;
	}
	
	public void setLine(String newLine) {
		this.line = newLine;
	}
	
	public String getLine() {
		return this.line;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	// needed to use collections.sort
	@Override
	public int compareTo(KWICMap k2) {
		return this.getKey().compareToIgnoreCase((k2.getKey()));
	}
}
