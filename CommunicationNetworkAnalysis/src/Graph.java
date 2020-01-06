
public class Graph {
	public static void outputAmountofNodesAndEdges() {
		System.out.println("How many Nodes? : " + ReadXML.Nodes.size());
		System.out.println("How many Edges? : " + ReadXML.edges_counter + "\n");
	}

	public static void outputNeigboursOfNodes() {
		for(GraphNode node : ReadXML.Nodes) {
			System.out.println("Node: " + node.getId() + " --> Neigbours: " + node.getNeigbours());
		}
	}
	
}
