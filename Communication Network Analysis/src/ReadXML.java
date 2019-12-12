import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/*	mithilfe der Quelle:	https://javarevisited.blogspot.com/2012/07
 * 							/read-file-line-by-line-java-example-scanner.html*/

//	Jede Line von der XML Datei wird in den Stringbuilder "file" hinzugefügt
//	Stringbuilder anstatt von String, weil es hier die .append() Funktion gibt

public class ReadXML {
	static StringBuilder file = null;
	
	
	public static void XmlToString(String filename) {
		try {
			file = new StringBuilder(); 
			
			FileInputStream fis = new FileInputStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			
			System.out.println("Reading XML File line by line using BufferedReader");
			
			String line = reader.readLine();
			
			// In dieser Schleife printen wir jede Line von der XML - Datei
			while(line != null) {
				file.append(line + "\n");
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String delims = "[>,<]";
		String[]test = file.toString().split(delims);
		System.out.println(test);
	}
	
	
	public static void getNodes(String file) {
		
	}
	
	public static void main(String[] args) throws IOException {
		XmlToString("small_graph (1).graphml");
	}
	
}
