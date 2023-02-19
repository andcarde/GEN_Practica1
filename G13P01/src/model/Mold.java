package model;

import java.util.List;

import model.chromosome.Gen;
import model.fitness.Fitness;

public class Mold implements MoldI {

	private final Fitness function;
	private final List<Gen> moldGenes;
	private Integer totalSize;
	
	public Mold(Fitness function, List<Gen> moldGenes) {
		this.function = function;
		this.moldGenes = moldGenes;
		this.totalSize = 0;
		for (Gen gen : moldGenes)
			this.totalSize += gen.getSize();
	}
	
	@Override
	public Fitness getFunction() {
		return this.function;
	}

	@Override
	public List<Gen> getGenes() {
		return this.moldGenes;
	}
	
	@Override
	public Integer getSize() {
		return this.totalSize;
	}
}
