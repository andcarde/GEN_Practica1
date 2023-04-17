package model.crossover;

import model.MoldI;
import model.gen.practice1.GenType;

public class CrossoverBuilder {

	public static CrossoverI build(CrossoverMethod selectionMethod, Double crossoverProbability,
			MoldI mold, GenType genType) {
		switch (genType) {
		case BINARY:
			switch (selectionMethod) {
			case ONE_POINT:
				return new BinaryOnePointCrossover(mold, crossoverProbability);
			case UNIFORM:
				return new UniformCrossover(mold, crossoverProbability);
			default:
				return null;
			}
		case REAL:
			switch (selectionMethod) {
			case ONE_POINT:
				return new RealOnePointCrossover(mold, crossoverProbability);
			case UNIFORM:
				return new UniformCrossover(mold, crossoverProbability);
			case ARITHMETIC :
				return new RealArithmeticCrossover(mold, crossoverProbability);
			case BLX_ALPHA :
				return new RealBLXCrossover(mold, crossoverProbability);
			default:
				return null;
			}
		case CITY:
			switch (selectionMethod) {
			case CO:
				return new OrdinalCoding(mold, crossoverProbability);
			case CX:
				return new CrossoverCycles(mold, crossoverProbability);
			case ERX:
				return new CrossoverRouteRecombination(mold, crossoverProbability);
			case AO:
				return new AlternativeOrder(mold, crossoverProbability);
			case PMX:
				return new CrossoverPartialPairing(mold, crossoverProbability);
			case OX:
				return new OrderCrossover(mold, crossoverProbability);
			case POX:
				return new PriorityOrderCrossover(mold, crossoverProbability);
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
