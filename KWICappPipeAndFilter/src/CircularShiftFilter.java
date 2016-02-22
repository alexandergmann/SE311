import java.util.ArrayList;

public class CircularShiftFilter implements Filter {
	private ArrayList<ArrayList<String>> pipeInput;
	private ArrayList<ArrayList<String>> pipeOutput;
	
	public CircularShiftFilter() {
		this.pipeInput =  new ArrayList<ArrayList<String>>();
	}
	
	public Pipe filter(Pipe pipe) {
		pipeInput = pipe.read();
		this.pipeOutput =  new ArrayList<ArrayList<String>>();
		ArrayList<String> stopWords = new ArrayList<String>();
		
		ArrayList<String> lines = pipeInput.get(0);
		if (pipeInput.size() > 1) {
			stopWords = pipeInput.get(1);
			
		}
		
		this.pipeOutput = this.shiftAllLines(lines, stopWords);		
		pipe.write(this.pipeOutput);		
		return pipe;
	}
	
	private ArrayList<ArrayList<String>> shiftAllLines(ArrayList<String> allLines, ArrayList<String> stopWords) {
		ArrayList<ArrayList<String>> allShiftedLines = new ArrayList<ArrayList<String>>();
		for (int lineNum = 0; lineNum < allLines.size(); lineNum++) {
			
			// split the lines into words
			String currentLine = allLines.get(lineNum);
			String[] allWords = currentLine.split("\\s");
			
			for (int i = 0; i < allWords.length; i++) {
				ArrayList<String> shiftedLine = new ArrayList<String>();			
				String newKeyword = allWords[i];
				// if the word of the current line we are shifting is a stop word, don't include it as a key
				if (!checkIfStopWord(newKeyword, stopWords)) {
				
					StringBuilder beforeKeyword = new StringBuilder();
					StringBuilder afterKeyword = new StringBuilder();
					StringBuilder newValue = new StringBuilder();
					
					// go through and make a string of the words before the keyword and after
					for (int j = 0; j < allWords.length; j++) {				
						if (j < i) {
							beforeKeyword.append(" ");
							beforeKeyword.append(allWords[j]);
						} else if ( j > i ) {
							afterKeyword.append(" ");
							afterKeyword.append(allWords[j]);
						}
					}
					// append before and after together then add all to the new kwicmap object
					newValue.append(afterKeyword.toString());
					newValue.append(beforeKeyword.toString());
					
					shiftedLine.add(newKeyword);
					shiftedLine.add(newValue.toString());					
					allShiftedLines.add(shiftedLine);
				}
			}			
		}		
		return allShiftedLines;
	}
	
	// goes through stop words to check if a word is one
	private boolean checkIfStopWord(String word, ArrayList<String> allStopWords) {
		boolean result = false;
		if (!allStopWords.isEmpty()) {
			for (int i = 0; i < allStopWords.size(); i++) {
				String stopWord = allStopWords.get(i);
				if(stopWord.equalsIgnoreCase(word)) {
					result = true;
				}
			}
		}
		return result;
	}
}
