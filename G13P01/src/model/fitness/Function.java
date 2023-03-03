package model.fitness;

public abstract class Function implements Fitness {

	protected Double precision;
	protected boolean isMaxim;
	
	public Function(Double precision) {
		this.precision = precision;
	}
	
	public boolean isMaximization() { return isMaxim;}	
}
