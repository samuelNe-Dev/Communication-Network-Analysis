import java.util.LinkedList;

public class GraphNode {
	    String name;
	    int num;
	    private boolean visited;
	    LinkedList<GraphEdge> edges;

	    GraphNode(String name ,int num) {
	        this.name = name;
	        this.num = num;
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
