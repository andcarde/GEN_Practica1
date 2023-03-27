package model.initialization;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.BoundedChromosome;
import model.chromosome.ChromosomeI;
import model.chromosome.GenType;
import model.chromosome.TravellerChromosome;
import model.mutation.CityMutationI;
import model.mutation.MutationI;

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
	public static List<ChromosomeI> act(GenType genType, Integer populationAmount, MoldI mold, MutationI mutation) {
		List<ChromosomeI> population = new ArrayList<>();
		switch (genType) {
		case BINARY:
		case REAL:
			for (int i = 0; i < populationAmount; i++) {
				population.add(new BoundedChromosome(mold));
			}
			break;
		case CITY:
			for (int i = 0; i < populationAmount; i++) {
				population.add(new TravellerChromosome(mold, (CityMutationI) mutation));
			}
			break;
		default:
			break;
		}
		return population;
	}
}
