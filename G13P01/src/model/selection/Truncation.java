package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;

public class Truncation implements SelectionI {
	
	private double truncationThreshold;
	private boolean isMaxim;
	
	public Truncation(Double truncationThreshold, boolean isMaximization) {
		this.truncationThreshold = truncationThreshold;
		isMaximization = isMaximization;
	}

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		int selectionLimit = (int) Math.ceil(population.size() * truncationThreshold) - 1;
		int j = 0;
		for (int i = 0; i < population.size(); i++) {
			selection.add(population.get(j).copy());
			if (j == selectionLimit)
				j = 0;
		}
		return selection;
	}
}
