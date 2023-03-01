package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Roulette implements SelectionI {

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		Positivizer.positivizeFitness(population);
		PopulationTable table = new PopulationTable(population);
		List<Double> accumulated = table.getAccumulated();
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
		double probability;
		for (int i = 0; i < population.size(); i++) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(accumulated, probability));
			selection.add(aux);
		}
		return selection;
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
