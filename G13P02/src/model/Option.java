package model;

import java.util.Map;

public class Option {

	private Map<String, String> options;
	
	public Option() {}
	
	public void setOption(String key, String value) {
		options.put(key, value);
	}
	
	public String get(String key) {
		return options.get(key);
	}
}
