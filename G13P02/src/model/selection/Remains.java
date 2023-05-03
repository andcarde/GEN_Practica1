package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeComparatorMax;
import model.chromosome.ChromosomeComparatorMin;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Remains implements SelectionI {
	
	private boolean isMaxim;

	public Remains(boolean isMaximization) {
		isMaxim = isMaximization;
	}

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		if (isMaxim) population.sort(new ChromosomeComparatorMax());
		else population.sort(new ChromosomeComparatorMin());
		PopulationTable table = new PopulationTable(population);
		List<Double> punctuactions = table.getPunctuation();
		List<Double> fitness = table.getFitness();

		if (!isMaxim) {
			fitness = corrigeMinimizar(fitness);
			punctuactions = table.getPunctuation(fitness);
		}
		// Primera parte
		for (int i = 0; i < population.size(); i++) {
			int number_of_copies = (int) Math.floor(punctuactions.get(i) * population.size());
			for (int j = 0; j < number_of_copies; j++)
				selection.add(population.get(i).copy());
		}
		
		// Segunda parte
		List<Double> accumulated = new ArrayList<>();
		double sum = 0.0;
		for (int i = 0; i < punctuactions.size(); i++) {
			sum += punctuactions.get(i);
			accumulated.add(sum);
		}
		double probability;
		while (selection.size() < population.size()) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(accumulated, probability)).copy();
			selection.add(aux);
		}
		
		return selection;
	}
	
	private List<Double> corrigeMinimizar(List<Double> fitness) {
		List<Double> ret = new ArrayList<>();
		for (int i = 0; i < fitness.size(); i++) {
			ret.add((1.05*fitness.get(fitness.size()-1))-fitness.get(i)); 
		}
		return ret;
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
