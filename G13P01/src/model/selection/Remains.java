package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Remains implements SelectionI {

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		PopulationTable table = new PopulationTable(population);
		List<Double> punctuactions = table.getPunctuation();
		List<ChromosomeI> ret = new ArrayList<>();
		//Primera parte
		for (int i = 0; i < population.size(); i++) {
			int number_of_copies = (int) (punctuactions.get(i)*population.size());
			int count = 0;
			while (count < number_of_copies) {
				ret.add(population.get(i).copy());
				count++;
			}
		}
		
		//Segunda parte
		List<Double> accumulated = table.getAccumulated();
		double probability;
		while (population.size() > ret.size()) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(accumulated, probability));
			ret.add(aux);
		}

		return ret;
	}
	
	private int getSelected(List<Double> accumulated, double prob) {
		int closest_index = 0;
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
