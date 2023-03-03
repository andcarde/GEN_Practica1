package model.mutation;

public class RealMutationBuilder {

	public static RealMutationI build(MutationMethod mutationMethod, Double mutationProbability) {
		switch (mutationMethod) {
		case BASIC:
			return new BasicRealMutation(mutationProbability);
		default:
			return null;
		}
	}
}
