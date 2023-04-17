package model.chromosome.practice3;

import model.mutation.MutationI;
import model.mutation.practice3.TreeMutationI;
import model.tree.ArithmeticNode;

public class TreeChromosome {

	private final TreeMutationI mutationMethod;
	private ArithmeticNode raiz;
	
	public TreeChromosome(TreeMutationI mutationMethod) {
		this.mutationMethod = mutationMethod;
	}
	
	public TreeChromosome(TreeChromosome chromosome) {
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
}
