package node;

public class Node {
	private String id;
	private int value;
	
	private Node (String new_id, int new_value){
		id = null;	
		id = new_id;
		value = new_value;
	}
	
	public String getId() {
		return id;
	}
	
	public int getValue() {
		return value;
	}
	
}
