package model.chromosome;

import model.mutation.MutationI;
import model.mutation.TreeMutationI;
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
		raiz = mutationMethod.act(this).getRaiz();
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
}
