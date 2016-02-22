import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputFilter {
	private ArrayList<ArrayList<String>> pipeInput;
	private ArrayList<ArrayList<String>> pipeOutput;
	
	public OutputFilter() {
		this.pipeInput = new ArrayList<ArrayList<String>> ();
	}
	
	public Pipe filter(Pipe pipe) {
		pipeInput = pipe.read();
		this.pipeOutput =  new ArrayList<ArrayList<String>>();
		String outFileName = "kwicResults.txt";
		this.writeToFile(outFileName, pipeInput);
		pipe.write(pipeOutput);
		return pipe;
	}
	// write to file, create a new one to clear the old every time
	public void writeToFile(String fileName, ArrayList<ArrayList<String>> lines) {	
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			File file = new File("fileName");
			
			if(!file.exists()) {
				file.createNewFile();
			}
			
			bufferedWriter.write("Key						Value");
			bufferedWriter.newLine();
			bufferedWriter.write("==============================================================");
			bufferedWriter.newLine();
			
			for(int i = 0; i < lines.size(); i++) {
				ArrayList<String> currentLine = lines.get(i);
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

}
