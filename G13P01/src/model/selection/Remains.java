package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Remains implements SelectionI {

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		PopulationTable table = new PopulationTable(population);
		
		// Primera parte
		List<Double> punctuactions = table.getPunctuation();
		for (int i = 0; i < population.size(); i++) {
			int number_of_copies = (int) Math.floor(punctuactions.get(i) * population.size());
			for (int j = 0; j < number_of_copies; j++)
				selection.add(population.get(i).copy());
		}
		
		// Segunda parte
		List<Double> accumulated = table.getAccumulated();
		double probability;
		while (selection.size() < population.size()) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(accumulated, probability)).copy();
			selection.add(aux);
		}
		
		return selection;
	}
	
	// Es correcto puesto que accumulated.get(accumulated.size() - 1) es siempre true
	private int getSelected(List<Double> accumulated, double prob) {
		for (int i = 0; i < accumulated.size(); i++)
			if (accumulated.get(i) > prob)
				return i;
		return 0;
	}
	
	/*
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
	*/
}
