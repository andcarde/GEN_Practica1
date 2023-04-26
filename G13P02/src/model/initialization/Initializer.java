package model.initialization;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.GenType;
import model.initialization.practice3.GrowPopulationInitializer;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.MutationI;
import model.mutation.practice3.TreeMutationI;

public class Initializer {

	/***
	 * Initializes the population (List<ChromosomeI>) with populationAmount individuals
	 * according to the genType introduced. The mutation parameter is needed for initialize
	 * the city chromosome which contains the form of mutation that could be experimented.
	 * @param genType
	 * @param populationAmount
	 * @param mold
	 * @param mutation
	 * @return the population
	 */
	public static List<ChromosomeI> act(GenType genType, Integer populationAmount, MoldI mold, MutationI mutation, int maxDepth, TreeInitializerEnum tie) {
		List<ChromosomeI> population = new ArrayList<>();
		switch (genType) {
		case TREE:
			TreeMutationI treeMutation = (TreeMutationI) mutation;
			switch(tie) {
				case FULL:
					break;
				case GROW:
					List<TreeChromosome> populationTree = new GrowPopulationInitializer(mold, maxDepth, populationAmount, treeMutation).initialize();
					for (ChromosomeI chr : populationTree)
						population.add(chr);
				case RAMPED_AND_HALP:
					break;
				default:
					break;
			}
		default:
			break;
		}
		return population;
	}
}
