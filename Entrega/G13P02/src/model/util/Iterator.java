package model.util;

import java.util.List;

public class Iterator {
	
	private List<Double> genoma;
	private int position;
	
	public Iterator(List<Double> genoma) {
		this.genoma = genoma;
	}
	
	public Iterator(List<Double> genoma, int initialPosition) {
		this.genoma = genoma;
		this.position = initialPosition;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	/***
	 * Returns the value of the genoma list and updates the index
	 * to the next position. When position reach the end of the array
	 * starts in the first position again.
	 * @return value
	 */
	public Double next() {
		position++;
		if (position == genoma.size())
			position = 0;
		return genoma.get(position);
	}
}
