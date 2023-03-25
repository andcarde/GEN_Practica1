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
	
	/*
	public static void main(String[] args) {
		Integer num_numbers = 20;
		Integer num_random = 10;
		
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < num_numbers; i++)
			numbers.add(i);
		
		List<Integer> indexs = new ArrayList<>();
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
	*/
	
	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		if (RandomGenerator.createAleatoryBoolean(1 - mutationProbability))
			return chromosome.getGenes();
		
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < chromosome.getSize(); i++)
			numbers.add(i);
		
		List<Integer> indexs = new ArrayList<>();
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
		ChromosomeI mutant = bt.getBestChromosome();
		return mutant.getGenes();
	}
	
	private class Backtracking {
		
		private ChromosomeVariator variator;
		private List<Double> values;
		private ChromosomeI actualChromosome;
		private ChromosomeI bestChromosome;
		private double bestFenotype;
		private boolean isMaximization;
		
		private Backtracking(ChromosomeI actualChromosome, List<Integer> indexs) {
			this.actualChromosome = actualChromosome;
			this.isMaximization = actualChromosome.getMold().getFunction().isMaximization();
			if (isMaximization)
				this.bestFenotype = Double.MIN_VALUE;
			else
				this.bestFenotype = Double.MAX_VALUE;
			
			this.values = new ArrayList<>();
			List<GenI> group = new ArrayList<>();
			List<GenI> genes = actualChromosome.getGenes();
			for (int i = 0; i < indexs.size(); i++) {
				GenI gen = genes.get(indexs.get(i));
				group.add(gen);
				values.add(gen.getValue());
			}
			this.variator = new ChromosomeVariator(group);
			
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
					this.bestChromosome = actualChromosome.copy();
					bestFenotype = actualFenotype;
				}
			}
			else {
				int variations = values.size();
				for (int i = 0; i < variations; i++) {
					Double d = values.remove(i);
					variator.set(d);
					recursive();
					variator.takeOff();
					values.add(i, d);
				}
			}
		}
		
		private ChromosomeI getBestChromosome() {
			return this.bestChromosome;
		}
	}
	
	private class ChromosomeVariator {
		
		private List<GenI> group;
		private int allocationCounter;
		
		private ChromosomeVariator(List<GenI> group) {
			this.group = group;
			allocationCounter = 0;
		}
		
		private void set(Double d) {
			group.get(allocationCounter).assimilate(d);
			allocationCounter++;
		}
		
		private void takeOff() {
			allocationCounter--;
		}
		
		private boolean isOver() {
			return allocationCounter == NUM_RANDOM;
		}
	}
}
