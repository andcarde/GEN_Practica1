package model;

import java.util.List;

import model.chromosome.BinaryGen;
import model.fitness.Fitness;

public class Mold implements MoldI {
	
	private final Fitness function;
	private final List<BinaryGen> moldGenes;
	private Integer totalSize;
	
	public Mold(Fitness function, List<BinaryGen> moldGenes) {
		this.function = function;
		this.moldGenes = moldGenes;
		this.totalSize = 0;
		for (BinaryGen gen : moldGenes)
			this.totalSize += gen.getSize();
	}
	
	@Override
	public Fitness getFunction() {
		return this.function;
	}

	@Override
	public List<BinaryGen> getGenes() {
		return this.moldGenes;
	}
	
	@Override
	public Integer getSize() {
		return this.totalSize;
	}
}
