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
		case FUNCTION4a:
			return new Function4a(precision, fuction4Dimension);
		case FUNCTION4b:
			return new Function4b(precision, fuction4Dimension);
		case CITIES:
			return new FunctionTSP();
		default:
			return null;
		}
	}
}
