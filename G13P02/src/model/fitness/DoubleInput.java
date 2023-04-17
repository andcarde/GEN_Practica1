package model.fitness;

import java.util.HashMap;
import java.util.Map;

public class DoubleInput implements Input<Double> {

	private Map<String, Double> map;
	
	public DoubleInput() {
		this.map = new HashMap<String, Double>();
	}
	
	public void put(String s, Double d) {
		this.map.put(s, d);
	}

	public Double get(String string) {
		return map.get(string);
	}
}
