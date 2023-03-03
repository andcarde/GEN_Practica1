package model.mutation;

public class MutationBuilder {

	public static RealMutationI build(MutationMethod mutationMethod, Double mutationProbability) {
		switch (mutationMethod) {
		case BASIC:
			return new BasicBinaryMutation(mutationProbability);
		default:
			return null;
		}
	}
}
