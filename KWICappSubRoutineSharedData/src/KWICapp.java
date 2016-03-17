import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KWICapp {
	/*
	 * Public variables shared throughout the class
	 */
	public ArrayList<ArrayList<String>> kwicSharedData = new ArrayList<ArrayList<String>>();
	
	/*
	 * Default constructor
	 */
	public KWICapp() {
		kwicSharedData = new ArrayList<ArrayList<String>>();
	}
	
	/*
	 * -------------------------------------------------------------------------------------------------------
	 * Input Subroutine here
	 * Reads the input files for the lines
	 * if there is a stop words file give, it reads that also 
	 * -------------------------------------------------------------------------------------------------------
	 */
	public void inputSubroutine() {
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> stopWords = new ArrayList<String>();
		String inFileName = null;
		String stopWordsFileName = null;
		
		ArrayList<String> fileNames = kwicSharedData.get(0);
		// data from this structure is cleared so that the output can be added
		kwicSharedData.clear();
		
		if (!fileNames.isEmpty()) {
			inFileName = fileNames.get(0);		
			if(fileNames.size() > 1) {
				stopWordsFileName = fileNames.get(1);				
			}
		}		
		// Read Lines From Files Here
		String line = null;
		if(inFileName != null) {
			try {
				line = null;
				// read lines from file
				FileReader inFileReader = new FileReader(inFileName);
				BufferedReader inBufferedReader = new BufferedReader(inFileReader);				
				while( (line = inBufferedReader.readLine()) != null ) {
					lines.add(line);
				}				
				inBufferedReader.close();				
				if (stopWordsFileName != null) {					
					line = null;
					// read lines from file
					FileReader stopFileReader = new FileReader(stopWordsFileName);
					BufferedReader stopBufferedReader = new BufferedReader(stopFileReader);					
					while( (line = stopBufferedReader.readLine()) != null ) {
						stopWords.add(line);
					}					
					stopBufferedReader.close();
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		// make sure all sentences are parsed since read line only works for each new line 
		ArrayList<String> parsedSentences = new ArrayList<String>();
		for (int i = 0; i < lines.size(); i++) {
			String[] sentences = lines.get(i).split("(\\.\\s+)|(\\n+)|(\\.)");
			for (int j = 0; j < sentences.length; j++) {
				parsedSentences.add(sentences[j]);
				}
		}		
		kwicSharedData.add(parsedSentences);
		kwicSharedData.add(stopWords);	
	}
	
	
	/*
	 * -------------------------------------------------------------------------------------------------------
	 * Circular Shifter Subroutine
	 * Gets all circular shifts for a line
	 * Does not add and shifts that's key is a stop word	 * 
	 * -------------------------------------------------------------------------------------------------------
	 */
	public void circularShifterSubroutine() {
		ArrayList<String> stopWords = new ArrayList<String>();		
		ArrayList<String> lines = kwicSharedData.get(0);
		if (kwicSharedData.size() > 1) {
			stopWords = kwicSharedData.get(1);			
		}		
		ArrayList<ArrayList<String>> allShiftedLines = new ArrayList<ArrayList<String>>();
		for (int lineNum = 0; lineNum < lines.size(); lineNum++) {			
			// split the lines into words
			String currentLine = lines.get(lineNum);
			String[] allWords = currentLine.split("\\s");
			
			for (int i = 0; i < allWords.length; i++) {
				ArrayList<String> shiftedLine = new ArrayList<String>();			
				String newKeyword = allWords[i];
				
				// if the word of the current line we are shifting is a stop word, don't include it as a key
				boolean isStopWord = false;
				if (!stopWords.isEmpty()) {
					// since ArrayList<string>.contains(string) is case sensitive
					// we loop through it and check using equalsIgnoreCase functions 
					for (int x = 0; x < stopWords.size(); x++) {
						String stopWord = stopWords.get(x);
						if(stopWord.equalsIgnoreCase(newKeyword)) {
							isStopWord = true;
						}
					}
				}
				
				if (!isStopWord) {				
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
		kwicSharedData = allShiftedLines;
	}	
	
	/*
	 * -------------------------------------------------------------------------------------------------------
	 * Sorting Subroutine
	 * Sorts the shifts alphabetically using the shifted lines key  
	 * -------------------------------------------------------------------------------------------------------
	 */
	public void  sortingSubRoutine() {		
		Collections.sort(kwicSharedData, new Comparator<ArrayList<String>>(){
			public int compare(ArrayList<String> a1, ArrayList<String> a2) {
				return a1.get(0).compareToIgnoreCase(a2.get(0));
			}			
		});		
	}
	
	/*
	 * -------------------------------------------------------------------------------------------------------
	 * Output Subroutine
	 * Outputs the KWIC keys and values to a text file
	 * -------------------------------------------------------------------------------------------------------
	 */
	public void outputSubroutine() {
		String outFileName = "kwicResults.txt";		
		try {
			FileWriter fileWriter = new FileWriter(outFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			File file = new File("fileName");
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			bufferedWriter.write("Key						Value");
			bufferedWriter.newLine();
			bufferedWriter.write("==============================================================");
			bufferedWriter.newLine();
			
			for(int i = 0; i < kwicSharedData.size(); i++) {
				ArrayList<String> currentLine = kwicSharedData.get(i);
				bufferedWriter.write(currentLine.get(0));
				bufferedWriter.write("						");
				bufferedWriter.write(currentLine.get(1));
				bufferedWriter.newLine();
			}		
			
			bufferedWriter.close();
			
		} catch (IOException e) {
			System.out.println("Could not write to file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * -------------------------------------------------------------------------------------------------------
	 * Main Function
	 * Calls all the subroutines sequentially
	 * -------------------------------------------------------------------------------------------------------
	 */
	public static void main(String[] args) throws Exception {
		// To avoid problems with static functions and variables, we create a new kwicapp in the main function
		KWICapp kwicApp = new KWICapp();
		ArrayList<String> argsList = new ArrayList<String>();
        String inFileName = null;
        String stopFileName = null;
        
       if(args.length > 0 ) {
    	   for(int i=0; i < args.length; i++) {
    		   String currentArg = args[i];
    		   if(currentArg.equals("-f")) {
    			   inFileName = args[i+1];
    		   } else if(currentArg.equals("-s")) {
    			   stopFileName = args[i+1];
    		   }
    	   }    	   
       } else {
    	   System.out.println("No inputs were given");
       }              
       if (!inFileName.isEmpty()) {
    	   
    	   argsList.add(inFileName);
    	   argsList.add(stopFileName);
    	   kwicApp.kwicSharedData.add(argsList);
    	   
    	   // call the input subroutine
    	   System.out.println("Start, read lines");
    	   kwicApp.inputSubroutine();
    	   
    	   // call the circular shifter subroutine
    	   System.out.println("shift Lines");
    	   kwicApp.circularShifterSubroutine(); 
    	   
    	   // call the sorting subroutine
           System.out.println("alphabetize Lines");
           kwicApp.sortingSubRoutine();
           
           // call the output subroutine
           System.out.println("write Lines");
           kwicApp.outputSubroutine();           
           // fin
           System.out.println("End");
       } else {
    	   System.out.println("-f arguement required");
       }
	}
}
