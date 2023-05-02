package model;

import java.util.List;

import model.fitness.Fitness;
import model.gen.practice3.GenI;
import model.mutation.MutationI;

public class Mold implements MoldI {
	
	private final Fitness function;
	private final List<GenI> moldGenes;
	private final boolean bloating;
	private final MutationI mutation;
	private final int populationAmount;
	private double k;
	
	public Mold(Fitness function, List<GenI> moldGenes, boolean bloating, MutationI mutation,
			int populationAmount) {
		this.function = function;
		this.moldGenes = moldGenes;
		this.bloating = bloating;
		this.mutation = mutation;
		this.populationAmount = populationAmount;
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
	
	@Override
	public int getPopulationAmount() {
		return populationAmount;
	}
	
	@Override
	public MutationI getMutation() {
		return mutation;
	}

	@Override
	public void setK(double k) {
		this.k = k;
	}
	
	@Override
	public double getK() {
		return k;
	}
}
