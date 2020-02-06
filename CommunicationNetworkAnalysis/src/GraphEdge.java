
public class GraphEdge implements Comparable<GraphEdge>{
	private GraphNode source;
	private GraphNode destination;
	private double weight;

	GraphEdge(GraphNode s, GraphNode d, double w) {
       
        source = s;
        destination = d;
        weight = w;
    }

	public void setSource(GraphNode src) {
		source = src;
	}
	public void setDestination(GraphNode dst) {
		destination = dst;
	}
	public void setWeight(double w) {
		weight = w;
	}
	
	public GraphNode getSource() {
		return source;
	}
	public GraphNode getDestination() {
		return destination;
	}
	public double getWeight() {
		return weight;
	}
	
	public String toString() {
	    return String.format("(%s -> %s, %f)", source.get_name(), destination.get_name(), weight);
	}

	public int compareTo(GraphEdge otherEdge) {

	    if (this.weight > otherEdge.weight) {
	        return 1;
	    }
	    else return -1;
	}



}