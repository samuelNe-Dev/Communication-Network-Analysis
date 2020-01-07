
public class GraphEdge implements Comparable<GraphEdge>{
	GraphNode source;
	GraphNode destination;
	double weight;

	GraphEdge(GraphNode s, GraphNode d, double w) {
       
        source = s;
        destination = d;
        weight = w;
    }


	public String toString() {
	    return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
	}

	public int compareTo(GraphEdge otherEdge) {

	    if (this.weight > otherEdge.weight) {
	        return 1;
	    }
	    else return -1;
	}



}