package model;

import java.util.HashMap;
import java.util.Map;

import model.crossover.Crossover;
import model.crossover.OnePointCrossover;
import model.crossover.UniformCrossover;
import model.fitness.Fitness;
import model.fitness.Function1;

public class Builder {

	public static Map<String, Object> build(Option op) {
		Map<String, Object> config = new HashMap<>();
		config.put("crossover", buildCrossover(op.get("crossover")));
		config.put("function", buildFunction(op.get("function")));
		config.put("function", buildFunction(op.get("function")));
		buildFitness(op.get("fitness"));
	}
	
	private static Crossover buildCrossover(String s) {
		if (s.equals("one_point"))
			return new OnePointCrossover();
		if (s.equals("uniform"))
			return new UniformCrossover();
		return null;
		
	}
	
	private static Fitness buildFunction(String s) {
		if (s.equals("f1"))
			return new Function1();
		if (s.equals("f2"))
			return new Function2();
		if (s.equals("f3"))
			return new Function3();
		if (s.equals("f4"))
			return new Function4();
		if (s.equals("f5"))
			return new Function5();
		return null;
		
	}
}
