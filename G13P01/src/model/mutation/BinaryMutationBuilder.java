package model.mutation;

public class BinaryMutationBuilder {

	public static BinaryMutationI build(MutationMethod mutationMethod, Double mutationProbability) {
		switch (mutationMethod) {
		case BASIC:
			return new BasicBinaryMutation(mutationProbability);
		default:
			return null;
		}
	}
}
