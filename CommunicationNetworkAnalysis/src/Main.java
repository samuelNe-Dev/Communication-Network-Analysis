import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
			FileNotFoundException, InterruptedException {
		/*
		 * Graph graph = new Graph(true); GraphNode zero = new GraphNode("0",0);
		 * GraphNode one = new GraphNode("1",1); GraphNode two = new GraphNode("2",2);
		 * GraphNode three = new GraphNode("3",3); GraphNode four = new
		 * GraphNode("4",4); GraphNode five = new GraphNode("5",5); GraphNode six = new
		 * GraphNode("6",6); graph.addEdge(zero, one, 8); graph.addEdge(zero, two, 11);
		 * graph.addEdge(one, three, 3); graph.addEdge(one, four, 8); graph.addEdge(one,
		 * two, 7); graph.addEdge(two, four, 9); graph.addEdge(three, four, 5);
		 * graph.addEdge(three, five, 2); graph.addEdge(four, six, 6);
		 * graph.addEdge(five, four, 1); graph.addEdge(five, six, 8);
		 * graph.DijkstraShortestPath(zero, five);
		 */
		System.out.println("Reading in File '" + args[0] + "'");

		Graph.getNodesAndEdgesFromXML(args[0]);
		Graph graph = new Graph(); // false --> not directed -> undirected graph
		graph.addAllEdges();

		System.out.println("### Graph information ###");
		System.out.println("Size of Nodes: " + ReadXML.Nodes.size());
		System.out.println("Size of Edges: " + ReadXML.Edges.size());

		// IDs NODES
		System.out.print("Vertex IDs: [");
		for (GraphNode node : ReadXML.Nodes) {
			System.out.print("'" + node.get_num() + "',");
		}
		System.out.println("]");

		// IDs EDGES
		System.out.print("Edge IDs: [");
		for (int i = 0; i < ReadXML.Edges.size(); i++) {
			System.out.print("'" + i + "',");
		}
		System.out.println("]");
		System.out.println("Graph connected: " + graph.checkConnectivity());
		System.out.println("Graph diameter: " + graph.diameter);



		
		

		if (args.length > 1) {
			switch (args[1]) {
			case "-s":
				graph.DijkstraShortestPath(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				break;
			case "-b":
				// Funktion für Betweeness mit einem Parameter -> Node an dem wir es anwenden
				// wollen
				// bsp. Ausführung(Terminal): java myJavaProgram inputfile.graphml -b 1
				break;
			case "-a":
				/*
				 * -a switch starts the calculation of all above mentioned properties and has a
				 * filename (outputfile.graphml) as parameter where to place the output.
				 */
				WriteXML write = new WriteXML(args[2]);
				Thread threadWrite = new Thread(write);
				threadWrite.setName("Write XML");
				threadWrite.start();
				// bsp. Ausführung(Terminal: java myJavaProgram inputfile . graphml -a
				// outputfile . graphml
				break;

			default:
			}
		}
		else {
			graph.getAllShortestPaths();
			System.out.println("Graph diameter: " + graph.diameter);
		}
	}

}
