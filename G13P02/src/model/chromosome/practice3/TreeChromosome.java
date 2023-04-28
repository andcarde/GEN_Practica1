package model.chromosome.practice3;

import java.util.List;

import model.Executor;
import model.MoldI;
import model.chromosome.Chromosome;
import model.fitness.CallbackInput;
import model.gen.practice3.ArithmeticNode;
import model.mutation.MutationI;
import model.mutation.practice3.TreeMutationI;
import model.util.Cast;
import model.util.Covariance;
import model.util.Variance;

public class TreeChromosome extends Chromosome {

	private final TreeMutationI mutationMethod;
	private ArithmeticNode raiz;
	
	public TreeChromosome(MoldI mold, TreeMutationI mutationMethod) {
		super(mold);
		this.mutationMethod = mutationMethod;
	}
	
	public TreeChromosome(TreeChromosome chromosome) {
		super(chromosome);
		this.mutationMethod = chromosome.mutationMethod;
		this.raiz = chromosome.raiz.copy();
	}
	
	public TreeChromosome copy() {
		return new TreeChromosome(this);
	}
	
	public Integer getSize() {
		return raiz.getNumSons();
	}
	
	public double getBasicValue() {
		if (phenotype == null) evaluate();
		return phenotype;
	}
	
	public void mutate() {
		raiz = mutationMethod.act(this);
	}
	
	@Override
	public Double getValue() {
		if (phenotype == null) evaluate();
		if (!mold.getBloating()) return phenotype;
		Executor executor = this.mold.getExecutor();
		List<TreeChromosome> population = Cast.castChromosomeToTree(executor.getPopulation());
		double k = Covariance.calculate(population) / Variance.calculate(population);
		return this.phenotype + k*raiz.getNumSons();
	}

	public String getGenesToString() {
		return raiz.toString();
	}
	
	public MutationI getMutation() {
		return mutationMethod;
	}

	public ArithmeticNode getRaiz() {
		return raiz;
	}

	public void setRaiz(ArithmeticNode raiz) {
		this.raiz = raiz;
	}
	
	public Double getValue(Double x, ArithmeticNode node) {
		return node.getValue(x);
	}

	@Override
	public void initialize() {}

	@Override
	public int indexOf(int city) {
		return 0;
	}
	
	@Override
	public void evaluate() {
		CallbackInput input = new CallbackInput();
		input.put("tree", raiz);
		this.phenotype = this.mold.getFunction().getValue(input);
	}
}
