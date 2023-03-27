package model;

import java.util.List;

import model.chromosome.BinaryGen;
import model.chromosome.GenI;
import model.fitness.Fitness;

public class Mold implements MoldI {
	
	private final Fitness function;
	private final List<GenI> moldGenes;
	private Integer totalSize;
	
	public Mold(Fitness function, List<GenI> moldGenes) {
		this.function = function;
		this.moldGenes = moldGenes;
		this.totalSize = 0;
		for (GenI gen : moldGenes) {
			if (gen instanceof BinaryGen)
				this.totalSize += ((BinaryGen) gen).getSize();
			else
				this.totalSize++;
		}
	}
	
	@Override
	public Fitness getFunction() {
		return this.function;
	}

	@Override
	public List<GenI> getGenes() {
		return this.moldGenes;
	}
	
	@Override
	public Integer getSize() {
		return this.totalSize;
	}

	@Override
	public Integer getNumGenes() {
		return this.moldGenes.size();
	}
}
