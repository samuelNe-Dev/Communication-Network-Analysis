import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Graph {
	public int counter = 0;
	private Set<GraphNode> nodes;

	// private boolean directed;
	public double diameter = 0;
	Graph() {
		// this.directed = directed;
		nodes = new HashSet<>();
	}
	public static void getNodesAndEdgesFromXML(String file)
			throws ParserConfigurationException, SAXException, IOException {
		ReadXML.getNodes(file);
		ReadXML.getEdges(file);
	}

	public GraphNode intToGraphNode(int n) {
		return ReadXML.Nodes.get(n);
	}

	public void addAllEdges() {
		for (int i = 0; i < ReadXML.Edges.size(); i++) {
			for (int j = 0; j < ReadXML.Nodes.size(); j++) {
				if (ReadXML.Nodes.get(j).get_name().equals(ReadXML.Edges.get(i).getSrc())) {
					for (int u = 0; u < ReadXML.Nodes.size(); u++) {
						if (ReadXML.Nodes.get(u).get_name().equals(ReadXML.Edges.get(i).getTrg())) {
							this.addEdge(j, u, ReadXML.Edges.get(i).getWeight());
						}
					}
				}
			}
		}
	}

	public void addEdge(int s, int d, double weight) {
		GraphNode source = intToGraphNode(s);
		GraphNode destination = intToGraphNode(d);
		nodes.add(source);
		nodes.add(destination);
		addEdgeHelper(source, destination, weight);
		if (source != destination) {
			addEdgeHelper(destination, source, weight);
		}
	}

	private void addEdgeHelper(GraphNode a, GraphNode b, double weight) {

		for (GraphEdge edge : a.get_edges()) {
			if (edge.getSource() == a && edge.getDestination() == b) {
				edge.setWeight(weight);
				return;
			}
		}

		a.get_edges().add(new GraphEdge(a, b, weight));
	}

	public void printEdges() {
		for (GraphNode node : nodes) {
			LinkedList<GraphEdge> edges = node.get_edges();

			if (edges.isEmpty()) {
				System.out.println("Node " + node.get_name() + " has no edges.");
				continue;
			}
			System.out.print("Node " + node.get_name() + " has edges to: ");

			for (GraphEdge edge : edges) {
				System.out.print(edge.getDestination().get_name() + "(" + edge.getWeight() + ") ");
			}
			System.out.println();
		}
	}

	public Boolean checkConnectivity() {
		Boolean connectivity = true;
		for (GraphNode node : nodes) {
			// LinkedList<GraphEdge> edges = node.edges; //we dont have to instanciate a new
			// object everytime
			if (node.get_edges().isEmpty()) {
				connectivity = false;
				break;
			}
		}
		return connectivity;
	}

	public  boolean hasEdge(GraphNode source, GraphNode destination) {
		LinkedList<GraphEdge> edges = source.get_edges();
		for (GraphEdge edge : edges) {
			if (edge.getDestination() == destination) {
				return true;
			}
		}
		return false;
	}

	public void resetNodesVisited() {
		for (GraphNode node : nodes) {
			node.unvisit();
		}
	}

	public void DijkstraShortestPath(int s, int e) {
		this.resetNodesVisited();
		GraphNode start = intToGraphNode(s);
		GraphNode end = intToGraphNode(e);

		HashMap<GraphNode, GraphNode> changedAt = new HashMap<>();
		changedAt.put(start, null);
		HashMap<GraphNode, Double> shortestPathMap = new HashMap<>();
		for (GraphNode node : nodes) {
			if (node == start)
				shortestPathMap.put(start, 0.0);
			else
				shortestPathMap.put(node, Double.POSITIVE_INFINITY);
		}
		for (GraphEdge edge : start.get_edges()) {
			shortestPathMap.put(edge.getDestination(), edge.getWeight());
			changedAt.put(edge.getDestination(), start);
		}

		start.visit();
		while (true) {
			GraphNode currentNode = closestReachableUnvisited(shortestPathMap);
			if (currentNode == null) {
				System.out.println("There isn't a path between " + start.get_name() + " and " + end.get_name());
				System.out.println("The path costs: 0");
				return;
			}
			if (currentNode == end) {
				System.out.println("The path with the smallest weight between " + start.get_name() + " and "
						+ end.get_name() + " is:");
				GraphNode child = end;
				String path = end.get_name();
				while (true) {
					GraphNode parent = changedAt.get(child);
					if (parent == null) {
						break;
					}
					path = parent.get_name() + " " + path;
					child = parent;
				}
				System.out.println(path);
				System.out.println("The path costs: " + shortestPathMap.get(end));
				if(diameter < shortestPathMap.get(end)) {
					diameter = shortestPathMap.get(end);
				}
				return;
			}
			currentNode.visit();
			for (GraphEdge edge : currentNode.get_edges()) {
				if (edge.getDestination().get_visited())
					continue;

				if (shortestPathMap.get(currentNode) + edge.getWeight() < shortestPathMap.get(edge.getDestination())) {
					shortestPathMap.put(edge.getDestination(), shortestPathMap.get(currentNode) + edge.getWeight());
					changedAt.put(edge.getDestination(), currentNode);
				}
			}
			
		}
	}

	private GraphNode closestReachableUnvisited(HashMap<GraphNode, Double> shortestPathMap) {

		double shortestDistance = Double.POSITIVE_INFINITY;
		GraphNode closestReachableNode = null;
		for (GraphNode node : nodes) {
			if (node.get_visited())
				continue;

			double currentDistance = shortestPathMap.get(node);
			if (currentDistance == Double.POSITIVE_INFINITY)
				continue;

			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				closestReachableNode = node;
			}
		}
		return closestReachableNode;
	}

	public void getAllShortestPaths() {
		for (int i = 0; i < ReadXML.Nodes.size(); ++i) {
			for (int j = 0; j < ReadXML.Nodes.size(); ++j) {
				this.DijkstraShortestPath(i, j);

			}
		}
	}

}
