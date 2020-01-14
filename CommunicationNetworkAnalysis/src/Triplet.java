public class Triplet {
	private String src;
	private String trg;
	private double weight;
	
	Triplet(String s, String t, double w){
		this.src = s;
		this.trg = t;
		this.weight = w;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String getTrg() {
		return trg;
	}
	public void setTrg(String trg) {
		this.trg = trg;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
