package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;

class Available {

	// Instance (Singleton pattern)
	private static Available available;
	
	// Map of available mutation method for each fitness function
	private Map<FitnessFunction, List<MutationMethod>> availableMutations;
	
	// Map of available crossover method for each fitness function
	private Map<FitnessFunction, List<CrossoverMethod>> availableCrossovers;
	
	/***
	 * All the available mutation and crossover method for each function is
	 * inserted in his correspondent map.
	 */
	private Available() {
		this.availableMutations = new HashMap<>();
		this.availableCrossovers = new HashMap<>();
	}
	
	/***
	 * Returns is the received mutationMethod is available for the received function.
	 * @param function
	 * @param mutationMethod
	 * @return
	 */
	static boolean isMutationAvailable(FitnessFunction function , MutationMethod mutationMethod) {
		if (Available.available == null)
			Available.available = new Available();
		List<MutationMethod> availableMutations = available.availableMutations.get(function);
		for (MutationMethod mm : availableMutations)
			if (mm == mutationMethod)
				return true;
		return false;
	}
	
	/***
	 * Returns is the received mutationMethod is available for the received crossover.
	 * @param function
	 * @param crossoverMethod
	 * @return
	 */
	static boolean isCrossoverAvailable(FitnessFunction function, CrossoverMethod crossoverMethod) {
		if (Available.available == null)
			Available.available = new Available();
		List<CrossoverMethod> availableCrossovers = available.availableCrossovers.get(function);
		for (CrossoverMethod cm : availableCrossovers)
			if (cm == crossoverMethod)
				return true;
		return false;
	}
}
