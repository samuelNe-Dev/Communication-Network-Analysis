import java.util.LinkedList;

public class GraphNode {
		int n;
	    String name;
	    private boolean visited;
	    LinkedList<GraphEdge> edges;

	    GraphNode(int n, String name) {
	        this.n = n;
	        this.name = name;
	        visited = false;
	        edges = new LinkedList<>();
	    }

	    boolean isVisited() {
	        return visited;
	    }

	    void visit() {
	        visited = true;
	    }

	    void unvisit() {
	        visited = false;
	    }
}
