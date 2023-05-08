package model.mutation.practice3;

import model.chromosome.Chromosome;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;
import model.mutation.MutationI;
import model.random.RandomGenerator;

public abstract class TreeMutation implements MutationI {
	
	private double mutationProbability;
	
	protected TreeMutation(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Chromosome> T act(T chromosome) {
		if (!RandomGenerator.createAleatoryBoolean(mutationProbability))
			return chromosome;
		TreeChromosome treeChromosome = (TreeChromosome) chromosome;
		mutateNode(treeChromosome.getRaiz());
		return (T) treeChromosome;
	}
	
	protected abstract void mutateNode(ArithmeticNode node);
}
