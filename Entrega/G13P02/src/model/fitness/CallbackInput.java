package model.fitness;

import java.util.HashMap;
import java.util.Map;

import model.fitness.practice3.Callback;

public class CallbackInput implements Input<Callback> {

private Map<String, Callback> map;
	
	public CallbackInput() {
		this.map = new HashMap<String, Callback>();
	}
	
	public void put(String s, Callback d) {
		this.map.put(s, d);
	}

	public Callback get(String string) {
		return map.get(string);
	}
}
