package model.mutation;

import model.gen.practice3.GenType;
import model.mutation.practice1.BasicBinaryMutation;
import model.mutation.practice3.ContractionTreeMutation;
import model.mutation.practice3.FunctionalTreeMutation;
import model.mutation.practice3.HoistTreeMutation;
import model.mutation.practice3.PermutationTreeMutation;

public class MutationBuilder {

	public static MutationI build(GenType gentype, MutationMethod mutationMethod, double mutationProbability) {
		switch(gentype) {
			case TREE:
				switch (mutationMethod) {
					case FUNCTIONAL:
						return new FunctionalTreeMutation(mutationProbability);
					case HOIST:
						return new ContractionTreeMutation(mutationProbability);
					case PERMUTATION:
						return new PermutationTreeMutation(mutationProbability);
					case TERMINAL:
						return new HoistTreeMutation(mutationProbability);
					default:
						break;
				}
				break;
			case BINARY:
				switch (mutationMethod) {
					case BINARY:
						return new BasicBinaryMutation(mutationProbability);
					default:
						break;
				}
				break;
		}
		return null;
	}
}
