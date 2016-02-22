import java.util.ArrayList;

public class Pipe {
	private ArrayList<ArrayList<String>> lines;
	
	public Pipe() {
		lines = new ArrayList<ArrayList<String>>();
		
	}
	
	public void write(ArrayList<ArrayList<String>> newLines) {
		this.lines = newLines;		
	}
	
	public ArrayList<ArrayList<String>> read() {
		return this.lines;
	}
}
