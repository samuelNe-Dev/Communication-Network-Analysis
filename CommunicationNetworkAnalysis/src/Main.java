import java.io.FileNotFoundException;
import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,FileNotFoundException {
		/*
		Graph graph = new Graph(true);
	    GraphNode zero = new GraphNode("0",0);
	    GraphNode one = new GraphNode("1",1);
	    GraphNode two = new GraphNode("2",2);
	    GraphNode three = new GraphNode("3",3);
	    GraphNode four = new GraphNode("4",4);
	    GraphNode five = new GraphNode("5",5);
	    GraphNode six = new GraphNode("6",6);
	    graph.addEdge(zero, one, 8);
	    graph.addEdge(zero, two, 11);
	    graph.addEdge(one, three, 3);
	    graph.addEdge(one, four, 8);
	    graph.addEdge(one, two, 7);
	    graph.addEdge(two, four, 9);
	    graph.addEdge(three, four, 5);
	    graph.addEdge(three, five, 2);
	    graph.addEdge(four, six, 6);
	    graph.addEdge(five, four, 1);
	    graph.addEdge(five, six, 8);
	    graph.DijkstraShortestPath(zero, five);
	    */
		
		ReadXML.inputFile(args[0]);
		Graph.getNodesAndEdgesFromXML(args[0]);
		System.out.println("Size of Nodes: " + ReadXML.Nodes.size());
		System.out.println("Size of Edges: " + ReadXML.Edges.size());
		Graph graph = new Graph(false); // false --> not directed -> undirected graph
		
		
		graph.addAllEdges();
		
		graph.DijkstraShortestPath(9, 30);
		
	}

}
