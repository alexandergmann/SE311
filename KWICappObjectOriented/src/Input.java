import java.io.IOException;
import java.util.ArrayList;

public interface Input {
	public ArrayList<String> getLines(String fileName) throws IOException;
}
