package model.mutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.random.RandomGenerator;

public class CityHeuristicMutation implements CityMutationI {

	private static final Integer NUM_RANDOM = 3;
	private Double mutationProbability;

	public CityHeuristicMutation(Double mutation) {
		mutationProbability = mutation;
	}
	
	public static <T> List<T> constructCopy(List<T> list) {
		List<T> listCopy = new ArrayList<>(list.size());
		Collections.copy(listCopy, list);
		return listCopy;
	}
	
	public static void main(String[] args) {
		Integer num_random = 10;
		
		List<Integer> indexs = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < num_random; i++)
			numbers.add(i);
		for (int i = 0; i < num_random; i++) {
			int random = RandomGenerator.createAleatoryInt(numbers.size());
			Integer index = numbers.remove(random);
			indexs.add(index);
		}
		
		System.out.print("\nLista:");
		for (Integer i : indexs)
			System.out.print(" " + i);
		
		indexs.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				if (i1 < i2)
					return -1;
				else
					return 1;
			}
		});
		
		System.out.print("\nLista:");
		for (Integer i : indexs)
			System.out.print(" " + i);
	}
	
	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		if (RandomGenerator.createAleatoryBoolean(1 - mutationProbability))
			return chromosome.getGenes();
		List<Integer> indexs = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < NUM_RANDOM; i++)
			numbers.add(i);
		for (int i = 0; i < NUM_RANDOM; i++) {
			int random = RandomGenerator.createAleatoryInt(numbers.size());
			Integer index = numbers.remove(random);
			indexs.add(index);
		}
		indexs.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				if (i1 < i2)
					return -1;
				else
					return 1;
			}
		});
		Backtracking bt = new Backtracking(chromosome, indexs);
		chromosome = bt.getBestChromosome();
		return chromosome.getGenes();
	}
	
	private class Backtracking {
		
		private ChromosomeVariator variator;
		private double bestFenotype;
		private ChromosomeI bestChromosome;
		private ChromosomeI actualChromosome;
		private List<GenI> group;
		private boolean isMaximization;
		
		private Backtracking(ChromosomeI actualChromosome, List<Integer> indexs) {
			this.actualChromosome = actualChromosome;
			isMaximization = actualChromosome.getMold().getFunction().isMaximization();
			variator = new ChromosomeVariator(indexs);
			group = new ArrayList<>();
			for (int i = indexs.size() - 1; i >= 0; i--)
				group.add(actualChromosome.getGenes().remove(i));
			if (isMaximization)
				bestFenotype = Double.MIN_VALUE;
			else
				bestFenotype = Double.MAX_VALUE;
			recursive();
		}
		
		private boolean isBetter(double d1, double d2) {
			if (isMaximization)
				return d1 > d2;
			else
				return d1 < d2;
		}
		
		public void recursive() {
			if (variator.isOver()) {
				actualChromosome.evaluate();
				double actualFenotype = actualChromosome.getValue();
				if (isBetter(actualFenotype, bestFenotype)) {
					bestChromosome = actualChromosome.copy();
					bestFenotype = actualFenotype;
				}
			}
			else {
				int variations = group.size();
				for (int i = 0; i < variations; i++) {
					GenI gen = group.remove(i);
					variator.set(gen, actualChromosome);
					recursive();
					variator.takeOff();
					group.add(i, gen);
				}
			}
		}
		
		private ChromosomeI getBestChromosome() {
			return this.bestChromosome;
		}
	}
	
	private class ChromosomeVariator {
		
		private List<Integer> indexs;
		private int allocationCounter;
		
		private ChromosomeVariator(List<Integer> indexs) {
			this.indexs = indexs;
			allocationCounter = 0;
		}
		
		private void set(GenI gen, ChromosomeI chromosome) {
			List<GenI> genes = chromosome.getGenes();
			genes.add(allocationCounter, gen);
			allocationCounter++;
		}
		
		private void takeOff() {
			allocationCounter--;
		}
		
		private boolean isOver() {
			return allocationCounter == indexs.size();
		}
	}
}
