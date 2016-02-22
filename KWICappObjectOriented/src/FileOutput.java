import java.io.*;
import java.util.ArrayList;

// class to handle output
public class FileOutput implements Output {
	
	public FileOutput() {
		
	}
	// write to file, create a new one to clear the old every time
	public void write(String fileName, ArrayList<KWICMap> sortedMap) {		
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
			
			for(int i = 0; i < sortedMap.size(); i++) {
				KWICMap currentMap = sortedMap.get(i);
				bufferedWriter.write(currentMap.getKey());
				bufferedWriter.write("						");
				bufferedWriter.write(currentMap.getValue());
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
