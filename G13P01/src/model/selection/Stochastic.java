package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Stochastic implements SelectionMethod {

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		PopulationTable table = new PopulationTable(population);
		List<Double> accumulated = table.getAccumulated();
		
		double distance = RandomGenerator.createAleatoryInt(1/population.size());
		for (int i = 0; i < population.size(); i++) {
			ChromosomeI aux = population.get(getSelected(accumulated, distance));
			selection.add(aux);
			distance += 1/population.size();
		}
		return selection;
	}
	
	private int getSelected(List<Double> accumulated, double prob) {
		int closest_index = -1;
		double closest_value = -10;
		for (int i = 0; i < accumulated.size(); i++) {
			if (prob < accumulated.get(i) && (accumulated.get(i) - prob < abs(prob - closest_value))) {
				closest_index = i;
				closest_value = accumulated.get(i);
			}
		}
		return closest_index;
	}
	
	private double abs(double val) {
		if (val < 0) return -val;
		return val;
	}


}
