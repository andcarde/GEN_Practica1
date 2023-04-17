package model.mutation.practice2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.gen.practice1.GenI;
import model.random.RandomGenerator;

public class CityHeuristicMutation implements CityMutationI {

	// Number of random positions to iterate
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
	
	/* ++ Main Method used for testing +++++++++++++++++++++++++++++++++++++++
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
	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
	
	/***
	 * This class implements a backtracking algorithm on the given
	 * chromosome at the indicated positions.
	 */
	
	private class Backtracking {
		
		// In charge of modifying the value of the genes with respect to the current permutation.
		private ChromosomeVariator variator;
		// Values of the gens (city identificators) to permutate
		private List<Double> values;
		// Current chromosome permutation
		private ChromosomeI actualChromosome;
		// Best chromosome permutation founded
		private ChromosomeI bestChromosome;
		// Phenotype of the best chromosome found
		private double bestFenotype;
		// Indicates is the quality of a chromosome increases if we increase or decrease its phenotype.
		private boolean isMaximization;
		
		/***
		 * After the execution of the class builder, the best permutation of genes will be
		 * found in the bestChromosome attribute. 
		 * @param actualChromosome : Chromosome to mutate.
		 * @param indexs : Positions selected to iterate above them.
		 */
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
		
		/***
		 * Returns is d1 is better than d2.
		 * @param d1
		 * @param d2
		 * @return
		 */
		private boolean isBetter(double d1, double d2) {
			if (isMaximization)
				return d1 > d2;
			else
				return d1 < d2;
		}
		
		/***
		 * Recursive function of backtracking.
		 */
		public void recursive() {
			/* The base case is achived when all the values has been coupled to the chromosome,
			 * so the chromosome is evaluated and replaced the best founded chromosome if is better
			 * that it.
			 */
			if (variator.isOver()) {
				actualChromosome.evaluate();
				double actualFenotype = actualChromosome.getValue();
				if (isBetter(actualFenotype, bestFenotype)) {
					this.bestChromosome = actualChromosome.copy();
					bestFenotype = actualFenotype;
				}
			}
			/* There are values (city identificator) have not been assigned to the chromosome,
			 * so each different remain value (in "values" attribute) is coupled to the chromosome
			 * in the actual position, the recursive call have placed until all values are assigned.
			 * This method makes the permutation of the values.
			 */
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
		
		/***
		 * Returns the best chromosome founded in the search.
		 * @return
		 */
		private ChromosomeI getBestChromosome() {
			return this.bestChromosome;
		}
	}
	
	/***
	 * This class makes the permutations possible registering the actual
	 * position and coupling the actual value to the corresponding gen.
	 */
	private class ChromosomeVariator {
		
		private List<GenI> group;
		private int allocationCounter;
		
		private ChromosomeVariator(List<GenI> group) {
			this.group = group;
			allocationCounter = 0;
		}
		
		/***
		 * Update the gen in the actual positions to the received value (d),
		 * also adds one from the position index.
		 * @param d
		 */
		private void set(Double d) {
			group.get(allocationCounter).assimilate(d);
			allocationCounter++;
		}
		
		/***
		 * Subtract one from the position index
		 */
		private void takeOff() {
			allocationCounter--;
		}
		
		/***
		 * Returns if all the values have been allocated in the genes.
		 * @return
		 */
		private boolean isOver() {
			return allocationCounter == NUM_RANDOM;
		}
	}
}
