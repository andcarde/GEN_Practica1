package model.mutation.practice1;

import model.gen.practice1.GenType;
import model.mutation.MutationI;

public class UnitaryMutationBuilder {

	public static MutationI build(GenType gentype, Double mutationProbability) {
		switch (gentype) {
		case BINARY:
			return new BasicBinaryMutation(mutationProbability);
		case REAL:
			return new BasicRealMutation(mutationProbability);
		default:
			return null;
		}
	}
}
