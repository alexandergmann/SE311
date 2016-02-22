import java.io.*;
import java.util.ArrayList;

// class to handle input
public class FileInput implements Input {
	
	public FileInput()	{
		
	}
	
	public ArrayList<String> getLines(String fileName) throws IOException	{
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

}
