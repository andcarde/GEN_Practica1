package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public abstract class Selection implements SelectionI {

	private boolean isMaximize;
	
	public Selection(boolean isMaximize) {
		this.isMaximize = isMaximize;
	}
	
	protected int compare(ChromosomeI chromosome1, ChromosomeI chromosome2) {
		if (chromosome1.getA
	}
	
	protected int compareAlter(ChromosomeI chromosome1, ChromosomeI chromosome2) {
		chromosome1.getA
	}
	
	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		// TODO Auto-generated method stub
		return null;
	}

}
