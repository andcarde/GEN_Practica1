package model;

import model.fitness.Fitness;
import model.mutation.MutationI;

public class Mold implements MoldI {
	
	private final Fitness function;
	private final boolean bloating;
	private final MutationI mutation;
	private final int maxHeight;
	private final int numWraps;
	private double k;
	
	public Mold(Fitness function, boolean bloating, MutationI mutation,
			int populationAmount, int maxHeight, int numWraps) {
		this.function = function;
		this.bloating = bloating;
		this.mutation = mutation;
		this.maxHeight = maxHeight;
		this.numWraps = numWraps;
	}
	
	@Override
	public Fitness getFunction() {
		return this.function;
	}
	
	@Override
	public boolean getBloating() {
		return bloating;
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

	@Override
	public int getMaxHeigth() {
		return maxHeight;
	}

	@Override
	public int getNumWraps() {
		return numWraps;
	}
}
