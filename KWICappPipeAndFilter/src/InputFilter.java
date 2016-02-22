import java.io.*;
import java.util.ArrayList;

public class InputFilter implements Filter {
	private ArrayList<ArrayList<String>> pipeInput;
	private ArrayList<ArrayList<String>> pipeOutput;
	
	public InputFilter() {
		this.pipeInput =  new ArrayList<ArrayList<String>>();
		this.pipeOutput =  new ArrayList<ArrayList<String>>();		
	}
	
	public Pipe filter(Pipe pipe) {
		pipeInput = pipe.read();
		pipeOutput = new ArrayList<ArrayList<String>>();
		ArrayList<String> args = pipeInput.get(0);
		
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> stopWords = new ArrayList<String>();
		String inFileName = null;
		String stopWordsFileName = null;
		
		if (!args.isEmpty()) {
			inFileName = args.get(0);		
			if(args.size() > 1) {
				stopWordsFileName = args.get(1);				
			}
		}
		
		if(inFileName != null) {
			try {
				lines = this.getLines(inFileName);
				
				if (stopWordsFileName != null) {
					stopWords = this.getLines(stopWordsFileName);			
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		
		
		lines = this.parseSentences(lines);
		pipeOutput.add(lines);
		pipeOutput.add(stopWords);
		pipe.write(pipeOutput);
		return pipe;	
	}
	
	private ArrayList<String> getLines(String fileName) throws IOException	{
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try {
			// read lines from file
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while( (line = bufferedReader.readLine()) != null ) {
				lines.add(line);			
			}			
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lines;
	}
	
	private ArrayList<String> parseSentences(ArrayList<String> lines) {
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
