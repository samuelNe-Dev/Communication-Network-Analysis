import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Graph {
	 
	 	private Set<GraphNode> nodes;
	    private boolean directed;
	    	    
	    Graph(boolean directed) {
	        this.directed = directed;
	        nodes = new HashSet<>();
	    }
	    public static void getNodesAndEdgesFromXML() throws ParserConfigurationException, SAXException, IOException {
	    	ReadXML.getNodes();
	    	ReadXML.getEdges();
	    }
	    
	    public GraphNode intToGraphNode(int n) {
	    	return ReadXML.Nodes.get(n);
	    }
	    
	    public void addAllEdges() {
	    	for(int i = 0; i < ReadXML.Edges.size(); i++) {
	    		for(int j = 0; j < ReadXML.Nodes.size(); j++) {
	    			if(ReadXML.Nodes.get(j).name.equals(ReadXML.Edges.get(i).getSrc())) {
	    				for(int u = 0; u < ReadXML.Nodes.size(); u++) {
	    					if(ReadXML.Nodes.get(u).name.equals(ReadXML.Edges.get(i).getTrg())) {
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
	        if (!directed && source != destination) {
	            addEdgeHelper(destination, source, weight);
	        }
	    }

	    private  void addEdgeHelper(GraphNode a, GraphNode b, double weight) {
	       
	        for (GraphEdge edge : a.edges) {
	            if (edge.getSource() == a && edge.getDestination() == b) {
	                edge.setWeight(weight);
	                return;
	            }
	        }
	       
	        a.edges.add(new GraphEdge(a, b, weight));
	    }
	    public void printEdges() {
	        for (GraphNode node : nodes) {
	            LinkedList<GraphEdge> edges = node.edges;

	            if (edges.isEmpty()) {
	                System.out.println("Node " + node.name + " has no edges.");
	                continue;
	            }
	            System.out.print("Node " + node.name + " has edges to: ");

	            for (GraphEdge edge : edges) {
	                System.out.print(edge.getDestination().name + "(" + edge.getWeight() + ") ");
	            }
	            System.out.println();
	        }
	    }
	    public boolean hasEdge(GraphNode source, GraphNode destination) {
	        LinkedList<GraphEdge> edges = source.edges;
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
	    	GraphNode start = intToGraphNode(s);
	    	GraphNode end = intToGraphNode(e);

	        HashMap<GraphNode, GraphNode> changedAt = new HashMap<>();
	        changedAt.put(start, null);
	        HashMap<GraphNode, Double> shortestPathMap = new HashMap<>();
	        for (GraphNode node : nodes) {
	            if (node == start)
	                shortestPathMap.put(start, 0.0);
	            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
	        }
	        for (GraphEdge edge : start.edges) {
	            shortestPathMap.put(edge.getDestination(), edge.getWeight());
	            changedAt.put(edge.getDestination(), start);
	        }

	        start.visit();
	        while (true) {
	            GraphNode currentNode = closestReachableUnvisited(shortestPathMap);
	            if (currentNode == null) {
	                System.out.println("There isn't a path between " + start.name + " and " + end.name);
	                return;
	            }
	            if (currentNode == end) {
	                System.out.println("The path with the smallest weight between "
	                                       + start.name + " and " + end.name + " is:");
	                GraphNode child = end;
	                String path = end.name;
	                while (true) {
	                    GraphNode parent = changedAt.get(child);
	                    if (parent == null) {
	                        break;
	                    }
	                    path = parent.name + " " + path;
	                    child = parent;
	                }
	                System.out.println(path);
	                System.out.println("The path costs: " + shortestPathMap.get(end));
	                return;
	            }
	            currentNode.visit();
	            for (GraphEdge edge : currentNode.edges) {
	                if (edge.getDestination().isVisited())
	                    continue;

	                if (shortestPathMap.get(currentNode)
	                   + edge.getWeight()
	                   < shortestPathMap.get(edge.getDestination())) {
	                    shortestPathMap.put(edge.getDestination(),
	                                       shortestPathMap.get(currentNode) + edge.getWeight());
	                    changedAt.put(edge.getDestination(), currentNode);
	                }
	            }
	        }
	    }
	    private GraphNode closestReachableUnvisited(HashMap<GraphNode, Double> shortestPathMap) {

	        double shortestDistance = Double.POSITIVE_INFINITY;
	        GraphNode closestReachableNode = null;
	        for (GraphNode node : nodes) {
	            if (node.isVisited())
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
}
