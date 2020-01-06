import java.util.ArrayList;

public class GraphNode {
	private final String id;
	private final int value;
	private ArrayList<Tuple> Neigbours;
	boolean connected;
	int distance;
	private int counter = 0;
	
	
	GraphNode(String n_id, int n_value){
		id = n_id;
		value = n_value;
		connected = false;
		distance = 1000000; //steht f√ºr Unendlich. Wird aber sp‰ter noch ge‰ndert das Unendlichkeit gibt
	}
	
	public String getId() {
		return this.id;
	}
	public int getValue() {
		return this.value;
	}
	
	public ArrayList<Tuple> getNeigbours() {
		return this.Neigbours;
	}
	
	public  boolean getConnected() {
		return this.connected;
	}
	public int getDistance() {
		return this.distance;
	}
	public void setNeigbours(GraphNode n_node, int weight2n) {
		Neigbours.set(counter, new Tuple(n_node,weight2n));
		counter++;
	}
	public void setConnected(boolean proof_connection) {
		connected = proof_connection;
	}
	public void setDistance(int new_distance) {
		distance = new_distance;
	}
	//public String shortestpath(GraphNode start,GraphNode destination) {
		//String thepath = null;
		//start.distance = 0;
		//start.connected = true;
		//for(int i =0; i < lengthofNeibours)
		
		//return thepath;
	//}
}





