package statistics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import control.Client;
import control.Controller;
import control.InvalidInputException;
import control.Request;
import control.RequestMaker;
import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;
import model.util.Pair;

public class StatisticGenerator implements RequestMaker, Client {
	
	// ------------------------- CONFIGURATION CONSTANTS -----------------------------
	private static final double CROSSOVER_RATE = 60;
	private static final double MUTATION_RATE = 5;
	private static final int POPULATION_AMOUNT = 200;
	private static final int GENERATION_AMOUNT = 100;
	private static final int TEST_NUMBER = 10;
	private static final int CONTESTANT_AMOUNT = 3;
	private static final int CHAMPION_PERCENTAGE = 70;
	private static final int TRUNCATION_PERCENTAGE = 25;
	private static FitnessFunction FITNESS_FUNCTION = FitnessFunction.ADAPTATION;
	private static final boolean BLOATING_VALUE = true;
	private static final int MAX_DEPTH = 5;
	private static final int WRAPS = 2;
	private static final TreeInitializerEnum initializationMethod = TreeInitializerEnum.RAMPED_AND_HALP;
	
	// -------------------------- PERMUTATIONS ----------------------------------------
	
	// -- Crossover method permutations
	private static final CrossoverMethod[] crossoverMethodArray = {
			CrossoverMethod.CROSSOVER_TREE
	};
	
	// -- Mutation method permutations
	private static final MutationMethod[] mutationMethodArray = {
			MutationMethod.FUNCTIONAL,
			MutationMethod.HOIST,
			MutationMethod.PERMUTATION,
			MutationMethod.TERMINAL
	};
	
	// -- Selection method permutations
	private static final SelectionMethod[] selectionMethodArray = { 
			SelectionMethod.RANKING,
			SelectionMethod.ROULETTE,
			SelectionMethod.PROBABILISTIC_TOURNAMENT
	};
	
	// Elitism permutations
	private static final double[] elitismRateArray = {0, 5};
	
	// Total number of tests to execute
	private static final int TOTAL = crossoverMethodArray.length * mutationMethodArray.length *
			selectionMethodArray.length * elitismRateArray.length;
	
	// ---------------------------- INFORMATION TO ACHIVE -----------------------
	
	// -- Score of the average of each crossover method
	private Map<CrossoverMethod, Double> crossoverResult;
	
	// -- Score of the average of each mutation method
	private Map<MutationMethod, Double> mutationResult;
	
	// +++++++++++++++++++++++++++++++++++++++++++
	// -- Best configuration
	private double bestAverage;
	private double bestElitismRate;
	// private boolean bestBloating;
	private SelectionMethod bestSelectionMethod;
	private CrossoverMethod bestCrossoverMethod;
	private MutationMethod bestMutationMethod;
	// +++++++++++++++++++++++++++++++++++++++++++
	
	// ------------------------- INFORMATION OF THE CURRENT TEST -----------------
	private double average;
	
	private double elitismRate;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	// ----------------------------------------------------------------------------
	
	private StatisticWriter statisticWriter;
	
	public StatisticGenerator() {
		this.crossoverResult = new HashMap<>();
		this.mutationResult = new HashMap<>();
		this.bestAverage = Double.MAX_VALUE;
		this.statisticWriter = new StatisticWriter();
	}
	
	/***
	 * Testing begins, there will be as many as permutations of the options in the
	 * configuration (elitism, selection, crossbreeding, and mutation) times 10, given
	 * that it is averaged.
	 */
	public void run() {
		for (CrossoverMethod c : crossoverMethodArray)
			crossoverResult.put(c, 0.0);
		for (MutationMethod m : mutationMethodArray)
			mutationResult.put(m, 0.0);
		int testNumber = 0;
		
		for (double e : elitismRateArray) {
			elitismRate = e;
			for (SelectionMethod s : selectionMethodArray) {
				selectionMethod = s;
				for (CrossoverMethod c : crossoverMethodArray) {
					crossoverMethod = c;
					for (MutationMethod m : mutationMethodArray) {
						mutationMethod = m;
						average = 0.0;
						for (int i = 0; i < TEST_NUMBER; i++) {
							try {
								Request request = new Request(this);
								Controller.getInstance().execute(request);
								Controller.getInstance().updateView(this, false);
							} catch (InvalidInputException iie) {}
						}
						average /= 10;
						uploadTest();
						testNumber++;
						System.out.println("Completed " + testNumber + "/" + TOTAL);
					}
				}
			}
		}
		
		for (CrossoverMethod c : crossoverMethodArray)
			crossoverResult.put(c, crossoverResult.get(c) * crossoverMethodArray.length / TOTAL);
		for (MutationMethod m : mutationMethodArray)
			mutationResult.put(m, mutationResult.get(m) * mutationMethodArray.length / TOTAL);
		uploadEnd();
		System.out.println("Statistics Completed!");
	}
	/***
	 * The Statistical Writer is instructed to insert a row with the test configuration
	 * (elitism, selection, crossover, and mutation) and the result. In addition, the best
	 * setting is overwritten if the current result is the best found.
	 */
	private void uploadTest() {
		crossoverResult.put(crossoverMethod, crossoverResult.get(crossoverMethod) + average);
		mutationResult.put(mutationMethod, mutationResult.get(mutationMethod) + average);
		
		if (average < bestAverage) {
			bestAverage = average;
			bestElitismRate = elitismRate;
			bestSelectionMethod = selectionMethod;
			bestCrossoverMethod = crossoverMethod;
			bestMutationMethod = mutationMethod;
		}
		
		statisticWriter.writeTest(
				String.valueOf(elitismRate),
				selectionMethod.name(),
				crossoverMethod.name(),
				mutationMethod.name(),
				String.valueOf(average)
		);

	}
	
	/***
	 * The Statistical Writer is instructed to insert a ranking with the selection methods
	 * and other ranking with the mutation methods, it will also have to write the best
	 * configuration obtained.
	 */
	private void uploadEnd() {
		List<Pair<String, Double>> crossoverList = new ArrayList<>();
		List<Pair<String, Double>> mutationList = new ArrayList<>();
		
		for (Entry<CrossoverMethod, Double> e : crossoverResult.entrySet())
			crossoverList.add(new Pair<String, Double>(e.getKey().name(), e.getValue()));
		for (Entry<MutationMethod, Double> e : mutationResult.entrySet())
			mutationList.add(new Pair<String, Double>(e.getKey().name(), e.getValue()));
		
		Comparator<Pair<String, Double>> comparator = new Comparator<Pair<String, Double>>() {
			public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
				if (o1.getR() < o2.getR())
					return -1;
				else if (o1.getR() > o2.getR())
					return 1;
				else
					return 0;
			}
		};
		
		crossoverList.sort(comparator);
		mutationList.sort(comparator);
		
		String[] crossoverNames = new String[crossoverMethodArray.length];
		String[] crossoverAverages = new String[crossoverMethodArray.length];
		String[] mutationNames = new String[mutationMethodArray.length];
		String[] mutationAverages = new String[mutationMethodArray.length];
		
		for (int i = 0; i < crossoverList.size(); i++) {
			crossoverNames[i] = crossoverList.get(i).getL();
			crossoverAverages[i] = String.valueOf(crossoverList.get(i).getR());
		}
		for (int i = 0; i < mutationList.size(); i++) {
			mutationNames[i] = mutationList.get(i).getL();
			mutationAverages[i] = String.valueOf(mutationList.get(i).getR());
		}
		
		statisticWriter.writeTables(crossoverNames, crossoverAverages, mutationNames, mutationAverages);
		
		statisticWriter.writeBest(
				String.valueOf(bestElitismRate),
				bestSelectionMethod.name(),
				bestCrossoverMethod.name(),
				bestMutationMethod.name(),
				String.valueOf(bestAverage)
		);
	}
	
	// ---------------------- RequestMaker METHODS -----------------------------
	@Override
	public String getPopulationAmount() {
		return String.valueOf(POPULATION_AMOUNT);
	}

	@Override
	public String getGenerationAmount() {
		return String.valueOf(GENERATION_AMOUNT);
	}

	@Override
	public String getCrossoverPercentage() {
		return String.valueOf(CROSSOVER_RATE);
	}

	@Override
	public String getMutationPercentage() {
		return String.valueOf(MUTATION_RATE);
	}
	
	@Override
	public String getSelectionMethod() {
		return selectionMethod.name();
	}

	@Override
	public String getCrossoverMethod() {
		return crossoverMethod.name();
	}

	@Override
	public String getMutationMethod() {
		return mutationMethod.name();
	}

	@Override
	public String getElitismPercentage() {
		return String.valueOf(elitismRate);
	}

	@Override
	public String getFitnessFunction() {
		return FITNESS_FUNCTION.name();
	}
	
	@Override
	public String getContestantsAmount() {
		return String.valueOf(CONTESTANT_AMOUNT);
	}

	@Override
	public String getChampionPercentage() {
		return String.valueOf(CHAMPION_PERCENTAGE);
	}

	@Override
	public String getTruncationPercentage() {
		return String.valueOf(TRUNCATION_PERCENTAGE);
	}
	
	// ------------- END of RequestMaker METHODS -----------------------------
	
	/***
	 * Client Method Implementation. It obtains the phenotype of the absolute leader
	 * of the current execution. This value is accumulated in average until NUMBER_TEST
	 * is executes times.
	 */
	@Override
	public void paintResult(double[] generationAverage, double[] generationLeaders,
			double[] absoluteLeaders, String bestChromosomeToString) {
		average += absoluteLeaders[GENERATION_AMOUNT - 1];
	}

	@Override
	public void paintP3Graphics(double[] idealFunction, double[] obtainedFunction, double[] xvalues) {}

	@Override
	public boolean isBloatingActive() {
		return BLOATING_VALUE;
	}

	@Override
	public String getInitializationMethod() {
		return initializationMethod.name();
	}

	@Override
	public String getMaxDepth() {
		return String.valueOf(MAX_DEPTH);
	}

	@Override
	public String getSeed() {
		return null;
	}

	@Override
	public String getWraps() {
		return String.valueOf(WRAPS);
	}

	@Override
	public String getGenType() {
		// TODO Auto-generated method stub
		return null;
	}
}
