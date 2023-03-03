package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public abstract class Selection implements SelectionI {

	private static boolean isMaximize;
	
	public Selection(boolean isMaximize) {
		this.isMaximize = isMaximize;
	}
	
	public static int compare(ChromosomeI chromosome1, ChromosomeI chromosome2) {
		if (chromosome1.getValue() < chromosome2.getValue()) {
			if (isMaximize) return 1;
			return -1;
		}
		if (chromosome1.getValue() > chromosome2.getValue()) {
			if (isMaximize) return -1;
			return 1;
		}
		return 0;
		
	}
	
	protected int compareAlter(ChromosomeI chromosome1, ChromosomeI chromosome2) {
		if (chromosome1.getAlterValue() < chromosome2.getAlterValue()) {
			if (isMaximize) return 1;
			return -1;
		}
		if (chromosome1.getAlterValue() > chromosome2.getAlterValue()) {
			if (isMaximize) return -1;
			return 1;
		}
		return 0;
	}
	
	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		// TODO Auto-generated method stub
		return null;
	}

}
