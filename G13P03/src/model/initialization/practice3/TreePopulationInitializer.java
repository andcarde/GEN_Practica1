package model.initialization.practice3;

import java.util.List;

import model.chromosome.practice3.TreeChromosome;

public interface TreePopulationInitializer {

	List<TreeChromosome> initialize();
}
