package model.fitness;

public class FunctionBuilder {

	public static Fitness build(FitnessFunction fitnessFunction, Double precision, Integer fuction4Dimension) {
		switch (fitnessFunction) {
		case FUNCTION1:
			return new Function1(precision);
		case FUNCTION2:
			return new Function2(precision);
		case FUNCTION3:
			return new Function3(precision);
		case FUNCTION4:
			return new Function4(precision, fuction4Dimension);
		default:
			return null;
		}
	}
}
