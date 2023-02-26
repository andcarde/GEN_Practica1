package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphic.Request;
import model.chromosome.Gen;
import model.crossover.CrossoverBuilder;
import model.crossover.CrossoverI;
import model.fitness.Fitness;
import model.fitness.FunctionBuilder;
import model.fitness.Variable;
import model.mutation.MutationBuilder;
import model.selection.Selection;
import model.selection.SelectionBuilder;

public class Builder {

	public static Map<String, Object> build(Request request) {
		Map<String, Object> config = new HashMap<>();
		config.put("generation_amount", request.getGenerationAmount());
		config.put("population_amount", request.getPopulationAmount());
		config.put("elitism_amount", request.getElitismProbability());
		MoldI mold = buildMold(request);
		config.put("mold", mold);
		config.put("selection", buildSelection(request));
		config.put("crossover", buildCrossover(request, mold));
		config.put("mutation", buildMutation(request));
		return config;
	}
	
	private static MoldI buildMold(Request request) {
		Fitness function = FunctionBuilder.build(request.getFitnessFunction(),
				request.getPrecision(), request.getFuction4Dimension());
		List<Variable> variables = function.getVariables();
		List<Gen> moldGenes = new ArrayList<>();
		for (Variable var : variables)
			moldGenes.add(Gen.build(var));
		return new Mold(function, moldGenes);
	}
	
	private static Selection buildSelection(Request request) {
		return SelectionBuilder.build(request.getSelectionMethod(), request.getTournamentRequest());
	}

	private static CrossoverI buildCrossover(Request request, MoldI mold) {
		return CrossoverBuilder.build(request.getCrossoverMethod(),
				request.getCrossoverProbability(), mold);
	}
	
	private static Object buildMutation(Request request) {
		return MutationBuilder.build(request.getMutationMethod(), request.getMutationProbability());
	}
}
