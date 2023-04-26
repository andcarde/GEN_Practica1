package model.fitness.practice3;

public class IntervalIterator {

	private final double upperLimit;
	private final double unit;
	private double pointer;
	
	public IntervalIterator(double belowLimit, double upperLimit, double unit) {
		this.upperLimit = upperLimit;
		this.unit = unit;
		this.pointer = belowLimit - unit;
	}
	
	public double next() {
		if (pointer < upperLimit)
			pointer += unit;
		return pointer;
	}
	
	public boolean hasNext() {
		return pointer < upperLimit;
	}

	public double getPointer() {
		return pointer;
	}
	
}
