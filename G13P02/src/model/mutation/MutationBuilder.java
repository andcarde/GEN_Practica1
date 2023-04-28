package model.mutation;

import model.gen.practice3.GenType;
import model.mutation.practice3.ContractionTreeMutation;
import model.mutation.practice3.FunctionalTreeMutation;
import model.mutation.practice3.PermutationTreeMutation;
import model.mutation.practice3.TerminalTreeMutation;
import model.mutation.practice3.TreeMutationI;

public class MutationBuilder {

	public static TreeMutationI build(GenType gentype, MutationMethod mutationMethod, Double mutationProbability) {
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
						return new TerminalTreeMutation(mutationProbability);
				}
		}
		return null;
	}
}
