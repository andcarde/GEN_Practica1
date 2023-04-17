package model.mutation;

import model.gen.practice1.GenType;
import model.mutation.practice1.BasicBinaryMutation;
import model.mutation.practice1.BasicRealMutation;
import model.mutation.practice2.CityEugenicMutation;
import model.mutation.practice2.CityExchangeMutation;
import model.mutation.practice2.CityHeuristicMutation;
import model.mutation.practice2.CityInsertionMutation;
import model.mutation.practice2.CityInverseMutation;

public class MutationBuilder {

	public static MutationI build(GenType gentype, MutationMethod mutationMethod, Double mutationProbability) {
		switch(gentype) {
			case BINARY:
				return new BasicBinaryMutation(mutationProbability);
			case REAL:
				return new BasicRealMutation(mutationProbability);
			case CITY:
				switch(mutationMethod) {
					case EXCHANGE:
						return new CityExchangeMutation(mutationProbability);
					case INVERSE:
						return new CityInverseMutation(mutationProbability);
					case INSERTION:
						return new CityInsertionMutation(mutationProbability);
					case EUGENIC:
						return new CityEugenicMutation(mutationProbability);
					case HEURISTIC:
						return new CityHeuristicMutation(mutationProbability);
					default:
						return null;
				}
			default:
				return null;
		}
	}
}
