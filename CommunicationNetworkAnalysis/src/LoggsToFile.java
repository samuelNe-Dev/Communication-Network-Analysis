import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class LoggsToFile {
	final static Logger LOGGER = Logger.getLogger(LoggsToFile.class.getName());

	public void write(String data) {
		try {
			FileWriter fw = new FileWriter(new File("/home/loggly/WriterClass/myFile.txt"));
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			
		}
	}
}
