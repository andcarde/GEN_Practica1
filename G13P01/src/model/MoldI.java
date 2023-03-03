package model;

import java.util.List;

import model.chromosome.GenI;
import model.fitness.Fitness;

public interface MoldI {

	Fitness getFunction();

	List<GenI> getGenes();

	Integer getSize();

	Integer getMeanSize();
}
