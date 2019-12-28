
public class Tuple {
	GraphNode node;
	int weight;
	
	Tuple(GraphNode n, int w){
		node = n;
		weight = w;
	}
	public void printTuple() {
		System.out.println(node + " ; " + weight);
	}
}
