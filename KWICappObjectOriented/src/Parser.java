import java.util.ArrayList;

// class used to parse lines
//though the file reader should take care of all situations involving new lines with new lines it doesn't handle periods and others
public class Parser {
	
	public Parser() {
		
	}
	
	public ArrayList<String> ParseLinesToSentences(ArrayList<String> lines) {
		ArrayList<String> parsedSentences = new ArrayList<String>();
		
		for (int i = 0; i < lines.size(); i++) {
		String[] sentences = lines.get(i).split("(\\.\\s+)|(\\n+)|(\\.)");
		for (int j = 0; j < sentences.length; j++) {
			parsedSentences.add(sentences[j]);
			}
		}
		return parsedSentences;
	}

}
