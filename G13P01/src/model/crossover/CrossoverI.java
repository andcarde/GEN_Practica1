package model.crossover;

import java.util.List;

import model.Chromosome;
import model.chromosome.ChromosomeI;

public interface CrossoverI {

	List<Chromosome> cross(ChromosomeI parent1, ChromosomeI parent2);
}
