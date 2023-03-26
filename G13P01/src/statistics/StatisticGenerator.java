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
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;
import model.util.Pair;

public class StatisticGenerator implements RequestMaker, Client {
	
	private static final double PRECISION = 0.001;
	private static final double CROSSOVER_RATE = 60;
	private static final double MUTATION_RATE = 5;
	private static final int POPULATION_AMOUNT = 200;
	private static final int GENERATION_AMOUNT = 100;
	private static final int TEST_NUMBER = 10;
	private static final int CONTESTANT_AMOUNT = 3;
	private static final int CHAMPION_PERCENTAGE = 70;
	private static final int FUNCTION4_DIMENSION = 2;
	private static final int TRUNCATION_PERCENTAGE = 25;
	private static FitnessFunction FITNESS_FUNCTION = FitnessFunction.CITIES;
	
	private static final CrossoverMethod[] crossoverMethodArray = {
			CrossoverMethod.AO,
			CrossoverMethod.CO,
			CrossoverMethod.CX,
			CrossoverMethod.ERX,
			CrossoverMethod.OX,
			CrossoverMethod.PMX,
			CrossoverMethod.POX
	};
	private static final MutationMethod[] mutationMethodArray = {
			MutationMethod.EUGENIC,
			MutationMethod.EXCHANGE,
			MutationMethod.HEURISTIC,
			MutationMethod.INSERTION,
			MutationMethod.INVERSE
	};
	private static final SelectionMethod[] selectionMethodArray = { 
			SelectionMethod.RANKING,
			SelectionMethod.ROULETTE,
			SelectionMethod.PROBABILISTIC_TOURNAMENT
	};
	private static final double[] elitismRateArray = {0, 5};
	
	private static final int TOTAL = crossoverMethodArray.length * mutationMethodArray.length *
			selectionMethodArray.length * elitismRateArray.length;
	
	private Map<CrossoverMethod, Double> crossoverResult;
	private Map<MutationMethod, Double> mutationResult;
	
	private double bestAverage;
	private double bestElitismRate;
	private SelectionMethod bestSelectionMethod;
	private CrossoverMethod bestCrossoverMethod;
	private MutationMethod bestMutationMethod;
	
	private double average;
	
	private double elitismRate;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	private StatisticWriter statisticWriter;
	
	public StatisticGenerator() {
		this.crossoverResult = new HashMap<>();
		this.mutationResult = new HashMap<>();
		this.bestAverage = Double.MAX_VALUE;
		this.statisticWriter = new StatisticWriter();
	}
	
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
								Controller.getInstance().updateView(this);
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
			crossoverResult.put(c, crossoverResult.get(c) / TOTAL);
		for (MutationMethod m : mutationMethodArray)
			mutationResult.put(m, mutationResult.get(m) / TOTAL);
		uploadEnd();
	}
	
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
		
		String[] crossoverNames = new String[selectionMethodArray.length];
		String[] crossoverAverages = new String[selectionMethodArray.length];
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
	public String getPrecision() {
		return String.valueOf(PRECISION);
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
	public String getFuction4Dimension() {
		return String.valueOf(FUNCTION4_DIMENSION);
	}

	@Override
	public String getTruncationPercentage() {
		return String.valueOf(TRUNCATION_PERCENTAGE);
	}
	
	@Override
	public void paintResult(double[] generationAverage, double[] generationLeaders,
			double[] absoluteLeaders, double[] selectivePressure, String bestChromosomeToString) {
		average += absoluteLeaders[GENERATION_AMOUNT - 1];
	}
}
