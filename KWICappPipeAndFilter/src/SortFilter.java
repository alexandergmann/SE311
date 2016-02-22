import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// class to handle alphabetizing
public class SortFilter implements Filter {
	private ArrayList<ArrayList<String>> pipeInput;
	private ArrayList<ArrayList<String>> pipeOutput;
	
	public SortFilter() {
		this.pipeInput = new ArrayList<ArrayList<String>> ();		
	}
	
	public Pipe filter(Pipe pipe) {
		pipeInput = pipe.read();
		this.pipeOutput =  new ArrayList<ArrayList<String>>();
		this.alphabetize(pipeInput);
		pipeOutput = pipeInput;
		pipe.write(pipeOutput);		
		return pipe;		
	}
	
	
	// use collections.sort, and create a custom comparable function for it
	public void alphabetize(ArrayList<ArrayList<String>> unsortedList) {
		Collections.sort(unsortedList, new Comparator<ArrayList<String>>(){
			public int compare(ArrayList<String> a1, ArrayList<String> a2) {
				return a1.get(0).compareToIgnoreCase(a2.get(0));
			}
			
		});
	}

}
