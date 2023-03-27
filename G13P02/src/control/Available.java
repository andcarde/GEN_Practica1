package control;

import java.util.ArrayList;
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
		
		List<FitnessFunction> numericFunctions = new ArrayList<>();
		numericFunctions.add(FitnessFunction.FUNCTION1);
		numericFunctions.add(FitnessFunction.FUNCTION2);
		numericFunctions.add(FitnessFunction.FUNCTION3);
		numericFunctions.add(FitnessFunction.FUNCTION4a);
		numericFunctions.add(FitnessFunction.FUNCTION4b);
		
		List<MutationMethod> numericMutations = new ArrayList<>();
		numericMutations.add(MutationMethod.BASIC);
		
		List<CrossoverMethod> numericCrossovers = new ArrayList<>();
		numericCrossovers.add(CrossoverMethod.ARITHMETIC);
		numericCrossovers.add(CrossoverMethod.BLX_ALPHA);
		numericCrossovers.add(CrossoverMethod.ONE_POINT);
		numericCrossovers.add(CrossoverMethod.UNIFORM);
		
		for (FitnessFunction f : numericFunctions) {
			this.availableMutations.put(f, numericMutations);
			this.availableCrossovers.put(f, numericCrossovers);
		}
		
		List<FitnessFunction> cityFunctions = new ArrayList<>();
		cityFunctions.add(FitnessFunction.CITIES);
		
		List<MutationMethod> cityMutations = new ArrayList<>();
		cityMutations.add(MutationMethod.EUGENIC);
		cityMutations.add(MutationMethod.EXCHANGE);
		cityMutations.add(MutationMethod.HEURISTIC);
		cityMutations.add(MutationMethod.INSERTION);
		cityMutations.add(MutationMethod.INVERSE);
		
		List<CrossoverMethod> cityCrossovers = new ArrayList<>();
		cityCrossovers.add(CrossoverMethod.CO);
		cityCrossovers.add(CrossoverMethod.CX);
		cityCrossovers.add(CrossoverMethod.ERX);
		cityCrossovers.add(CrossoverMethod.OX);
		cityCrossovers.add(CrossoverMethod.AO);
		cityCrossovers.add(CrossoverMethod.PMX);
		cityCrossovers.add(CrossoverMethod.POX);
		
		for (FitnessFunction f : cityFunctions) {
			this.availableMutations.put(f, cityMutations);
			this.availableCrossovers.put(f, cityCrossovers);
		}
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
