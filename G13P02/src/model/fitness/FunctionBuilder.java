package model.fitness;

import model.fitness.practice3.AdaptationFunction;

public class FunctionBuilder {

	public static Fitness build(FitnessFunction fitnessFunction, Double precision, Integer fuction4Dimension) {
		switch (fitnessFunction) {
		case ADAPTATION:
			return new AdaptationFunction();
		default:
			return null;
		}
	}
}
