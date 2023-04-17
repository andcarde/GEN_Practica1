package model.fitness;

import model.fitness.practice1.Function1;
import model.fitness.practice1.Function2;
import model.fitness.practice1.Function3;
import model.fitness.practice1.Function4a;
import model.fitness.practice1.Function4b;
import model.fitness.practice2.FunctionTSP;
import model.fitness.practice3.AdaptationFunction;

public class FunctionBuilder {

	public static Fitness build(FitnessFunction fitnessFunction, Double precision, Integer fuction4Dimension) {
		switch (fitnessFunction) {
		case FUNCTION1:
			return new Function1(precision);
		case FUNCTION2:
			return new Function2(precision);
		case FUNCTION3:
			return new Function3(precision);
		case FUNCTION4a:
			return new Function4a(precision, fuction4Dimension);
		case FUNCTION4b:
			return new Function4b(precision, fuction4Dimension);
		case CITIES:
			return new FunctionTSP();
		case ADAPTATION:
			return new AdaptationFunction();
		default:
			return null;
		}
	}
}
