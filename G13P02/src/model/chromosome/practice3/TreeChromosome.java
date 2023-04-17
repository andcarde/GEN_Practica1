package model.chromosome.practice3;

import model.MoldI;
import model.chromosome.Chromosome;
import model.gen.practice3.ArithmeticNode;
import model.mutation.MutationI;
import model.mutation.practice3.TreeMutationI;

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
	
	public void mutate() {
		raiz = mutationMethod.act(this);
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
}
