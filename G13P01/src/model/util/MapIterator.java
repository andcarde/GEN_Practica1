package model.util;

import java.util.Map;
import java.util.Set;

public class MapIterator {
	
	private Map<Double, Double> map;
	private Double value;
	private Set<Double> set;
	
	public MapIterator(Map<Double, Double> map, Double value, Set<Double> set) {
		this.map = map;
		this.value = value;
		this.set = set;
	}
	
	/***
	 * Returns the next city of the parent genome that is not contained in the set.
	 * It also add the value to the set.
	 * @return value
	 */
	public Double next() {
		do {
			value = map.get(value);
		} while (set.contains(value));
		set.add(value);
		return value;
	}
}
