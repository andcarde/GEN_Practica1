package model.initialization;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.practice3.CodonChromosome;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.GenType;
import model.initialization.practice3.FullPopulationInitializer;
import model.initialization.practice3.GrowPopulationInitializer;
import model.initialization.practice3.RampedAndHalfPopulationInitializer;
import model.initialization.practice3.TreeInitializerEnum;
import model.util.Cast;

public class Initializer {

	/***
	 * Initializes the population (List<ChromosomeI>) with populationAmount individuals
	 * according to the genType introduced. The mutation parameter is needed for initialize
	 * the chromosome which contains the form of mutation that could be experimented.
	 * @param genType
	 * @param populationAmount
	 * @param mold
	 * @param mutation
	 * @return the population
	 */
	public static List<ChromosomeI> act(GenType genType, Integer populationAmount, MoldI mold,
			int maxDepth, TreeInitializerEnum tie) {
		List<ChromosomeI> population = new ArrayList<>();
		switch (genType) {
		case GRAMATICA_EVOLUTIVA:
			List<TreeChromosome> populationCodon = new ArrayList<>();
			for (int i = 0; i < populationAmount; i++)
				populationCodon.add(new CodonChromosome(mold));
			return Cast.castTreeToChromosome(populationCodon);	
		case PROGRAMACION_EVOLUTIVA:
			List<TreeChromosome> populationTree = null;
			switch(tie) {
				case FULL:
					populationTree = new FullPopulationInitializer(mold, maxDepth,
							populationAmount).initialize();
					break;
				case GROW:
					populationTree = new GrowPopulationInitializer(mold, maxDepth,
							populationAmount).initialize();
					break;
				case RAMPED_AND_HALP:
					populationTree = new RampedAndHalfPopulationInitializer(mold, maxDepth,
							populationAmount).initialize();
					break;
			}
			return Cast.castTreeToChromosome(populationTree);
		}
		return population;
	}
}
