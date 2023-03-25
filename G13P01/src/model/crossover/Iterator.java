package model.crossover;

import java.util.List;

class Iterator {
	
	private List<Double> genoma;
	private int position;
	
	Iterator(List<Double> genoma, int initialPosition) {
		this.genoma = genoma;
		this.position = initialPosition;
	}
	
	Double next() {
		position++;
		if (position == genoma.size())
			position = 0;
		return genoma.get(position);
	}
}
