import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,FileNotFoundException {
		
		/*ReadXML.inputFile();
		ReadXML.getNodes();
		ReadXML.getEdges();
		
		System.out.println("\n\n----------------------------");
		System.out.println("Graph Communication Analysis");
		System.out.println("----------------------------\n");*/
		
		Graph.outputAmountofNodesAndEdges();
		 GraphWeighted graphWeighted = new GraphWeighted(true);
	        NodeWeighted zero = new NodeWeighted(0, "0");
	        NodeWeighted one = new NodeWeighted(1, "1");
	        NodeWeighted two = new NodeWeighted(2, "2");
	        NodeWeighted three = new NodeWeighted(3, "3");
	        NodeWeighted four = new NodeWeighted(4, "4");
	        NodeWeighted five = new NodeWeighted(5, "5");
	        NodeWeighted six = new NodeWeighted(6, "6");

	        graphWeighted.addEdge(zero, one, 8);
	        graphWeighted.addEdge(zero, two, 11);
	        graphWeighted.addEdge(one, three, 3);
	        graphWeighted.addEdge(one, four, 8);
	        graphWeighted.addEdge(one, two, 7);
	        graphWeighted.addEdge(two, four, 9);
	        graphWeighted.addEdge(three, four, 5);
	        graphWeighted.addEdge(three, five, 2);
	        graphWeighted.addEdge(four, six, 6);
	        graphWeighted.addEdge(five, four, 1);
	        graphWeighted.addEdge(five, six, 8);

	        graphWeighted.DijkstraShortestPath(zero, five);
		
	}

}
