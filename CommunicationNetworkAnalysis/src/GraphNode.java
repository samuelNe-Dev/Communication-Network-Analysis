import java.util.LinkedList;

public class GraphNode {
	    private String name;
	    private int num;
	    private boolean visited;
	    private LinkedList<GraphEdge> edges;
	    
	    //Overloaded Constructor
	    GraphNode(String name ,int num) {
	        this.name = name;
	        this.num = num;
	        this.visited = false;
	        this.edges = new LinkedList<>();
	    }

	    //Getter and Setter for name, num and edges
	    public String get_name() {
	    	return this.name;
	    }
	    
	    public int get_num() {
	    	return this.num;
	    }
	    
	    public LinkedList<GraphEdge> get_edges(){
	    	return this.edges;
	    }
	    
	    public void set_name(String new_name) {
	    	this.name = new_name;
	    }
	    
	    public void set_num(int new_num) {
	    	this.num = new_num;
	    }
	    
	    public void set_edges(LinkedList<GraphEdge> new_edges) {
	    	this.edges = new_edges;
	    }
	    
	    
	    
	    
	    //Getter and Setter for visited
	    public boolean get_visited() {
	        return this.visited;
	    }

	    void visit() {
	    	this.visited = true;
	    }

	    void unvisit() {
	        this.visited = false;
	    }
}