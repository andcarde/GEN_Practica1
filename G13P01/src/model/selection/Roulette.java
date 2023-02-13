package model.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class Roulette implements SelectionMethod {

	@Override
	public List<ChromosomeI> select(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		PopulationTable table = new PopulationTable(population);
		List<Double> accumulated = table.getAccumulated();
		BinaryTree<ChromosomeI> binaryTree = new BinaryTree<>();
		for (int i = 0; i < population.size(); i++)
			binaryTree.add(population.get(i), accumulated.get(i));
		ChromosomeI chromosome;
		Double probability;
		for (int i = 0; i < table.getAmount(); i++) {
			probability = RandomGenerator.createAleatoryDouble();
			chromosome = binaryTree.find(probability);
			selection.add(chromosome);
		}
		return selection;
	}
}
