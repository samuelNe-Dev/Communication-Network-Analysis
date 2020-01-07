import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
	 private Set<GraphNode> nodes;
	    private boolean directed;

	    Graph(boolean directed) {
	        this.directed = directed;
	        nodes = new HashSet<>();
	    }
	    public void addEdge(GraphNode source, GraphNode destination, double weight) {
	       
	        nodes.add(source);
	        nodes.add(destination);
	        addEdgeHelper(source, destination, weight);
	        if (!directed && source != destination) {
	            addEdgeHelper(destination, source, weight);
	        }
	    }

	    private void addEdgeHelper(GraphNode a, GraphNode b, double weight) {
	       
	        for (GraphEdge edge : a.edges) {
	            if (edge.source == a && edge.destination == b) {
	                edge.weight = weight;
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
	                System.out.print(edge.destination.name + "(" + edge.weight + ") ");
	            }
	            System.out.println();
	        }
	    }
	    public boolean hasEdge(GraphNode source, GraphNode destination) {
	        LinkedList<GraphEdge> edges = source.edges;
	        for (GraphEdge edge : edges) {
	            if (edge.destination == destination) {
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
	    public void DijkstraShortestPath(GraphNode start, GraphNode end) {
	        HashMap<GraphNode, GraphNode> changedAt = new HashMap<>();
	        changedAt.put(start, null);
	        HashMap<GraphNode, Double> shortestPathMap = new HashMap<>();
	        for (GraphNode node : nodes) {
	            if (node == start)
	                shortestPathMap.put(start, 0.0);
	            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
	        }
	        for (GraphEdge edge : start.edges) {
	            shortestPathMap.put(edge.destination, edge.weight);
	            changedAt.put(edge.destination, start);
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
	                if (edge.destination.isVisited())
	                    continue;

	                if (shortestPathMap.get(currentNode)
	                   + edge.weight
	                   < shortestPathMap.get(edge.destination)) {
	                    shortestPathMap.put(edge.destination,
	                                       shortestPathMap.get(currentNode) + edge.weight);
	                    changedAt.put(edge.destination, currentNode);
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
