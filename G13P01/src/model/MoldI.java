package model;

import java.util.List;

import model.chromosome.BinaryGen;
import model.fitness.Fitness;

public interface MoldI {

	Fitness getFunction();

	List<BinaryGen> getGenes();

	Integer getSize();
}
