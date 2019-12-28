import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,FileNotFoundException {
		
		ReadXML.inputFile();
		ReadXML.getNodes();
		ReadXML.getEdges();
		
		System.out.println("\n\n----------------------------");
		System.out.println("Graph Communication Analysis");
		System.out.println("----------------------------\n");
		
		Graph.outputAmountofNodesAndEdges();

		
	}

}
