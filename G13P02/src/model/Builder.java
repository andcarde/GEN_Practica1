package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import control.Request;
import model.crossover.CrossoverBuilder;
import model.crossover.CrossoverI;
import model.fitness.Fitness;
import model.fitness.FunctionBuilder;
import model.gen.practice3.GenI;
import model.gen.practice3.GenType;
import model.mutation.MutationBuilder;
import model.mutation.MutationI;
import model.selection.SelectionBuilder;
import model.selection.SelectionI;

public class Builder {

	private static MoldI mold;
	public static Map<String, Object> build(Request request) {
		Map<String, Object> config = new HashMap<>();
		config.put("generation_amount", request.getGenerationAmount());
		config.put("population_amount", request.getPopulationAmount());
		config.put("elitism_amount", request.getElitismProbability());
		mold = buildMold(request);
		config.put("mold", mold);
		config.put("selection", buildSelection(request));
		config.put("crossover", buildCrossover(request, mold, mold.getFunction().getGenType()));
		config.put("mutation", buildMutation(request, mold.getFunction().getGenType()));
		config.put("gen_type", mold.getFunction().getGenType());
		config.put("bloating", mold.getBloating());
		return config;
	}
	
	private static MoldI buildMold(Request request) {
		Fitness function = FunctionBuilder.build(request.getFitnessFunction());
		List<GenI> moldGenes = new ArrayList<>();
		return new Mold(function, moldGenes, request.isBloatingActive());
	}
	
	private static SelectionI buildSelection(Request request) {
		return SelectionBuilder.build(request.getSelectionMethod(), request.getTournamentRequest(), mold.getFunction().isMaximization(),request.getTruncationAmount());
	}

	private static CrossoverI buildCrossover(Request request, MoldI mold, GenType genType) {
		return CrossoverBuilder.build(request.getCrossoverMethod(),
				request.getCrossoverProbability(), mold, genType);
	}
	
	private static MutationI buildMutation(Request request, GenType gen) {
		return MutationBuilder.build(gen, request.getMutationMethod(), request.getMutationProbability());
	}
}
