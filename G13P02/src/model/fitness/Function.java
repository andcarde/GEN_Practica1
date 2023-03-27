package model.fitness;

public abstract class Function implements Fitness {

	protected boolean isMaxim;
	
	public Function() {}
	
	public boolean isMaximization() { return isMaxim;}	
}
