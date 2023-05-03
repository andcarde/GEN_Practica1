package model.crossover;

import java.util.List;

import model.chromosome.ChromosomeI;

public interface CrossoverI {

	List<ChromosomeI> act(List<ChromosomeI> population);
}
