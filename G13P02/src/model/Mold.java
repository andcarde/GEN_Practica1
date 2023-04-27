package model;

import java.util.List;

import model.fitness.Fitness;
import model.gen.practice3.GenI;

public class Mold implements MoldI {
	
	private final Fitness function;
	private final List<GenI> moldGenes;
	private boolean bloating;
	
	public Mold(Fitness function, List<GenI> moldGenes, boolean bloating) {
		this.function = function;
		this.moldGenes = moldGenes;
		this.bloating = bloating;
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
	public Integer getNumGenes() {
		return this.moldGenes.size();
	}

	@Override
	public boolean getBloating() {
		return bloating;
	}
}
