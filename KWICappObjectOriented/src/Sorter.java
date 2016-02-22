import java.util.ArrayList;
import java.util.Collections;

// class to handle alphabetizing
public class Sorter {
	
	public Sorter() {
		
	}
	
	// since kwicmap implemented a comparable, collections.sort can be used to put in order
	public void alphabetize(ArrayList<KWICMap> unsortedList) {
		Collections.sort(unsortedList);
	}

}
