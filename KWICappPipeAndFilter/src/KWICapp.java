import java.util.ArrayList;

public class KWICapp {
	
	public static void main(String[] args) throws Exception {
		Pipe pipe = new Pipe();
		ArrayList<ArrayList<String>> pipeInput = new ArrayList<ArrayList<String>>();
		ArrayList<String> argsList = new ArrayList<String>();
        String inFileName = null;
        String stopFileName = null;
        InputFilter inputFilter = new InputFilter();
        CircularShiftFilter shifter = new CircularShiftFilter();
        SortFilter sortFilter = new SortFilter();
        OutputFilter outputFilter = new OutputFilter();
        
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
    	   pipeInput.add(argsList);
    	   pipe.write(pipeInput);
    	   System.out.println("Start, read lines");
    	   pipe = inputFilter.filter( pipe );
    	   System.out.println("shift Lines");
    	   pipe = shifter.filter(  pipe );
           System.out.println("alphabetize Lines");
           pipe = sortFilter.filter( pipe );
           System.out.println("write Lines");
           pipe = outputFilter.filter( pipe );
           System.out.println("End");
       } else {
    	   System.out.println("-f arguement required");
       }
	}
}
