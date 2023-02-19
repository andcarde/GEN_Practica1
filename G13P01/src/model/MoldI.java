package model;

import java.util.List;

import model.chromosome.Gen;
import model.fitness.Fitness;

public interface MoldI {

	Fitness getFunction();

	List<Gen> getGenes();

	Integer getSize();
}
