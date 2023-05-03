package tests;

import java.util.List;

import model.Mold;
import model.MoldI;
import model.chromosome.ChromosomeI;
import model.fitness.Fitness;
import model.fitness.FitnessFunction;
import model.fitness.FunctionBuilder;
import model.gen.practice3.GenType;
import model.initialization.Initializer;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.MutationBuilder;
import model.mutation.MutationI;
import model.mutation.MutationMethod;

public class InitializationTest {

	public static void main(String[] args) {
		List<ChromosomeI> population = createPopulation();
		for (ChromosomeI chromosome : population)
			System.out.println(chromosome.toString());
	}
	
	private static List<ChromosomeI> createPopulation() {
		GenType genType = GenType.TREE;
		int populationAmount = 10;
		MoldI mold = createMold(populationAmount, genType);
		int maxDepth = 5;
		TreeInitializerEnum tie = TreeInitializerEnum.FULL;
		return Initializer.act(genType, populationAmount, mold, maxDepth, tie);
	}
	
	private static MoldI createMold(int populationAmount, GenType genType) {
		Fitness function = createFitness();
		boolean bloating = false;
		MutationI mutation = createMutation(genType);
		int maxHeight = 5;
		int numWraps = 2;
		return new Mold(function, bloating, mutation, populationAmount, maxHeight, numWraps);
	}
	
	private static MutationI createMutation(GenType genType) {
		MutationMethod mutationMethod =  MutationMethod.FUNCTIONAL;
		double mutationProbability = 0.5;
		return MutationBuilder.build(genType, mutationMethod, mutationProbability);
	}

	private static Fitness createFitness() {
		FitnessFunction fitnessFunction = FitnessFunction.ADAPTATION;
		return FunctionBuilder.build(fitnessFunction);
	}
}
