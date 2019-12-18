package edge;

public class Edge {
	private String source;
	private String target;
	private int edge;
	private int weight;
	
	public Edge (String new_source, String new_target, int new_edge, int new_weight) {
		source = null;	
		source = new_source;
		target = null;	
		target = new_target;
		edge = new_edge;
		weight = new_weight;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getTarget(){
		return target;
	}
	
	public int getEdge(){
		return edge
				;
	}
	
	public int getWeight(){
		return weight;
	}
}
