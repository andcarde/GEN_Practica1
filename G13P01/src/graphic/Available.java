package graphic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;

public class Available {

	private static Available available;
	private HashMap<FitnessFunction, List<MutationMethod>> availableMutations;
	private HashMap<FitnessFunction, CrossoverMethod> availableCrossover;
	
	private Available() {
		List<FitnessFunction> numericFunctions = new ArrayList<>();
		numericFunctions.add(FitnessFunction.FUNCTION1);
		numericFunctions.add(FitnessFunction.FUNCTION2);
		numericFunctions.add(FitnessFunction.FUNCTION3);
		numericFunctions.add(FitnessFunction.FUNCTION4a);
		numericFunctions.add(FitnessFunction.FUNCTION4b);
		
		for (FitnessFunction f : numericFunctions) {
			this.availableCrossover.put(f, null);
		}
		this.availableCrossover.put(FitnessFunction.FUNCTION1, null);
		this.availableMutation.put(null, null);
	}
	
	
	
	static boolean isAvailable(FitnessFunction g , MutationMethod m) {
		if (Available.available == null)
			available = new Available();
		List<MutationMethod> availableMutations = available.availableMutations.get(g);
		for (MutationMethod mut : availableMutations)
			if (m == mut)
				return true;
		return false;
	}
}
