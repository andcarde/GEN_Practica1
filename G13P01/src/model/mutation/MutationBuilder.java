package model.mutation;

public class MutationBuilder {

	public static MutationI build(MutationMethod mutationMethod, Double mutationProbability) {
		switch (mutationMethod) {
		case BASIC:
			return new BasicMutation(mutationProbability);
		default:
			return null;
		}
	}
}
