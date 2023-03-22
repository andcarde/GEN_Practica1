package model.mutation;

import model.chromosome.GenType;

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
					// TODO case EUGENESIC:
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
