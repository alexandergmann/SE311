import java.util.ArrayList;
import java.util.Comparator;

public class KWICapp {
	
	public static void main(String[] args) throws Exception {
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<String> stopWords = new ArrayList<String>();
        ArrayList<KWICMap> keywordMap = new ArrayList<KWICMap>();
        String inFileName = null;
        String stopFileName = null;
        String outFileName = "kwicResults.txt";
        Input input = new FileInput();
        CircularShifter shifter = new CircularShifter();
        Parser parser = new Parser();
        Sorter sorter = new Sorter();
        Output output = new FileOutput();
        
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
    	   System.out.println("Start, read lines");
    	   lines = input.getLines(inFileName);
    	   System.out.println("Parse Lines");
    	   lines = parser.ParseLinesToSentences(lines);
    	   
    	   if(!stopFileName.isEmpty()) {
    		   System.out.println("read stop Lines");
    		   stopWords = input.getLines(stopFileName);
    	   }

    	   System.out.println("shift Lines");
           keywordMap = shifter.shiftAllLines(lines, stopWords);
           System.out.println("alphabetize Lines");
           sorter.alphabetize(keywordMap);
           System.out.println("write Lines");
           output.write(outFileName, keywordMap);
           System.out.println("End");
       }
	}
}
