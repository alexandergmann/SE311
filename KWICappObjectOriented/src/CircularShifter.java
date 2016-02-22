import java.util.ArrayList;

public class CircularShifter {
	
	public CircularShifter() {

	}
	
	public ArrayList<KWICMap> shiftAllLines(ArrayList<String> allLines, ArrayList<String> stopWords) {
		ArrayList<KWICMap> allLinesShift = new ArrayList<KWICMap>();
		for (int lineNum = 0; lineNum < allLines.size(); lineNum++) {
			
			// split the lines into words
			String currentLine = allLines.get(lineNum);
			String[] allWords = currentLine.split("\\s");
			
			for (int i = 0; i < allWords.length; i ++) {
				KWICMap newMap = new KWICMap();				
				String newKeyword = allWords[i];
				// if the word of the current line we are shifting is a stop word, don't include it as a key
				if (!checkIfStopWord(newKeyword, stopWords)) {
				
					StringBuilder beforeKeyword = new StringBuilder();
					StringBuilder afterKeyword = new StringBuilder();
					StringBuilder newValue = new StringBuilder();
					
					// go through and make a string of the words befor the keyword and after
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
					newMap.setKey(newKeyword);
					newMap.setValue(newValue.toString());
					newMap.setLine(currentLine);
					allLinesShift.add(newMap);
				}
			}
			
		}		
		return allLinesShift;
	}
	
	// goes through stop words to check if a word is one
	private boolean checkIfStopWord(String word, ArrayList<String> allStopWords) {
		boolean result = false;
		for (int i = 0; i < allStopWords.size(); i++) {
			String stopWord = allStopWords.get(i);
			if(stopWord.equalsIgnoreCase(word)) {
				result = true;
			}
		}
		
		return result;
	}

}
