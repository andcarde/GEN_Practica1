package model.chromosome;

import model.fitness.DoubleVariable;
import model.mutation.RealMutationI;
import model.random.RandomGenerator;

public class RealGen implements RealGenI {
	
	private final RealMutationI mutationMethod;
	private final String name;
	private final Double belowLimit;
	private final Double upperLimit;
	private Double real;

	public static BoundedGenI build(DoubleVariable variable, RealMutationI mutationMethod) {
		String name = variable.getName();
		Double belowLimit = variable.getBelowLimit();
		Double upperLimit = variable.getUpperLimit();
		return new RealGen(mutationMethod, name, belowLimit, upperLimit);
	}
	
	private RealGen(RealMutationI mutationMethod, String name, Double belowLimit, Double upperLimit) {
		this.mutationMethod = mutationMethod;
		this.name = name;
		this.belowLimit = belowLimit;
		this.upperLimit = upperLimit;
	}
	
	private RealGen(RealGen gen) {
		this.mutationMethod = gen.mutationMethod;
		this.name = gen.name;
		this.belowLimit = gen.belowLimit;
		this.upperLimit = gen.upperLimit;
		this.real = gen.real;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getValue() {
		return this.real;
	}

	@Override
	public void initialize() {
		this.real = RandomGenerator.createAleatoryDouble() * (this.upperLimit - this.belowLimit) + this.belowLimit;
	}
	
	@Override
	public void assimilate(Double real) {
		this.real = real;
	}
	
	@Override
	public BoundedGenI copy() {
		return new RealGen(this);
	}

	@Override
	public void mutate() {
		this.mutationMethod.act(this);
	}

	@Override
	public Object getGenoma() {
		return this.real;
	}

	@Override
	public BoundedGenI assimilate(Object genoma) {
		this.real = (Double) genoma;
		return this;
	}

	@Override
	public Double getBelowLimit() {
		return this.belowLimit;
	}

	@Override
	public Double getWidth() {
		return this.upperLimit - this.belowLimit;
	}
}
