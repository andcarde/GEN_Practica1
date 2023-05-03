package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeComparatorMax;
import model.chromosome.ChromosomeComparatorMin;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Roulette implements SelectionI {
	private boolean isMaximization;

	public Roulette(boolean maxim) {
		isMaximization = maxim;
	}
	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		if (isMaximization) population.sort(new ChromosomeComparatorMax());
		else population.sort(new ChromosomeComparatorMin());
		Positivizer.positivizeFitness(population);
		PopulationTable table = new PopulationTable(population);
		List<Double> fitness = table.getFitness();
		List<Double> punctuactions;
		/*BinaryTree<ChromosomeI> binaryTree = new BinaryTree<>();
		for (int i = 0; i < population.size(); i++)
			binaryTree.add(population.get(i), accumulated.get(i));
		ChromosomeI chromosome;
		Double probability;
		for (int i = 0; i < table.getAmount(); i++) {
			probability = RandomGenerator.createAleatoryDouble();
			chromosome = binaryTree.find(probability);
			selection.add(chromosome);
		}*/
		if (!isMaximization) {
			fitness = corrigeMinimizar(fitness);
			punctuactions = table.getPunctuation(fitness);
			
		}
		else {
			fitness = corrigeMaximizar(fitness);
			punctuactions = table.getPunctuation(fitness);
		}
		List<Double> accumulated = new ArrayList<>();
		double sum = 0.0;
		for (int i = 0; i < punctuactions.size(); i++) {
			sum += punctuactions.get(i);
			accumulated.add(sum);
		}
		double probability;
		for (int i = 0; i < population.size(); i++) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(accumulated, probability));
			selection.add(aux);
		}
		return selection;
	}
	
	private List<Double> corrigeMaximizar(List<Double> fitness) {
		List<Double> ret = new ArrayList<>();
		for (int i = 0; i < fitness.size(); i++) {
			ret.add(Math.abs(fitness.get(fitness.size()-1))+fitness.get(i)); 
		}
		return ret;
	}
	private List<Double> corrigeMinimizar(List<Double> fitness) {
		List<Double> ret = new ArrayList<>();
		for (int i = 0; i < fitness.size(); i++) {
			ret.add((1.05*fitness.get(fitness.size()-1))-fitness.get(i)); 
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
