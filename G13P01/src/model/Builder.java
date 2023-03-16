package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphic.Request;
import model.chromosome.BinaryGen;
import model.chromosome.CityGen;
import model.chromosome.GenI;
import model.chromosome.GenType;
import model.chromosome.RealGen;
import model.crossover.CrossoverBuilder;
import model.crossover.CrossoverI;
import model.fitness.DoubleVariable;
import model.fitness.Fitness;
import model.fitness.FunctionBuilder;
import model.fitness.Variable;
import model.mutation.BinaryMutationBuilder;
import model.mutation.BinaryMutationI;
import model.mutation.MutationBuilder;
import model.mutation.MutationI;
import model.mutation.RealMutationBuilder;
import model.mutation.RealMutationI;
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
		return config;
	}
	
	private static MoldI buildMold(Request request) {
		Fitness function = FunctionBuilder.build(request.getFitnessFunction(),
				request.getPrecision(), request.getFuction4Dimension());
		List<Variable> variables = function.getVariables();
		List<GenI> moldGenes = new ArrayList<>();
		for (Variable var : variables) {
			if (function.getGenType() == GenType.BINARY)
				moldGenes.add(BinaryGen.build((DoubleVariable) var, buildBinaryMutation(request)));
			else if (function.getGenType() == GenType.REAL)
				moldGenes.add(RealGen.build((DoubleVariable) var, buildRealMutation(request)));
			else if (function.getGenType() == GenType.CITY)
				moldGenes.add(CityGen.build(var));
		}
		return new Mold(function, moldGenes);
	}
	
	private static SelectionI buildSelection(Request request) {
		return SelectionBuilder.build(request.getSelectionMethod(), request.getTournamentRequest(), mold.getFunction().isMaximization(),request.getTruncationAmount());
	}

	private static CrossoverI buildCrossover(Request request, MoldI mold, GenType genType) {
		return CrossoverBuilder.build(request.getCrossoverMethod(),
				request.getCrossoverProbability(), mold, genType);
	}
	
	private static BinaryMutationI buildBinaryMutation(Request request) {
		return BinaryMutationBuilder.build(request.getMutationMethod(), request.getMutationProbability());
	}
	
	private static RealMutationI buildRealMutation(Request request) {
		return RealMutationBuilder.build(request.getMutationMethod(), request.getMutationProbability());
	}
	
	private static MutationI buildMutation(Request request, GenType gen) {
		return MutationBuilder.build(gen, request.getMutationMethod(), request.getMutationProbability());
	}
}
