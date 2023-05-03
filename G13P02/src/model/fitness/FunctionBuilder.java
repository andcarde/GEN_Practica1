package model.fitness;

import model.fitness.practice3.AdaptationFunction;
import model.gen.practice3.GenType;

public class FunctionBuilder {

	public static Fitness build(FitnessFunction fitnessFunction, GenType gen) {
		switch (fitnessFunction) {
		case ADAPTATION:
			return new AdaptationFunction(gen);
		default:
			return null;
		}
	}
}
