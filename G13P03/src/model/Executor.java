package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.chromosome.ChromosomeComparatorMax;
import model.chromosome.ChromosomeComparatorMin;
import model.chromosome.ChromosomeI;
import model.crossover.CrossoverI;
import model.fitness.practice3.AdaptationFunction;
import model.initialization.Initializer;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.PopulationMutation;
import model.selection.SelectionI;
import model.util.Covariance;
import model.util.Variance;

public class Executor {

	// MODEL CONSTRAINTS -----------------------------------------------
	private final Integer GENERATION_AMOUNT;
	private final Integer POPULATION_AMOUNT;
	private final Integer ELITISM_AMOUNT;
	private final MoldI mold;
	private final SelectionI selection;
	private final CrossoverI crossover;
	private TreeInitializerEnum initialization;
	// ------------------------------------------------------------------
	
	private List<ChromosomeI> population;
	private List<ChromosomeI> elitism;
	
	// MODEL STATISTICS -------------------------------------------------
	private double[] generationAverage;
	// List of the best individuals of each promotion
	private double[] generationLeaders;
	// List of the best absolute individuals
	private double[] generationsAbsoluteLeaders;
	private ChromosomeI intergenerationLeader;
	private double[] selectivePressure;
	// ------------------------------------------------------------------
	
	// COMPARATOR -------------------------------------------------------
	private Comparator<ChromosomeI> comparator;
	// Not Used
	// private Observer observer;
	
	public Executor(Map<String, Object> config) {
		this.GENERATION_AMOUNT = (Integer) config.get("generation_amount");
		this.POPULATION_AMOUNT = (Integer) config.get("population_amount");
		this.ELITISM_AMOUNT = (Integer) config.get("elitism_amount") * POPULATION_AMOUNT / 100;
		this.mold = (MoldI) config.get("mold");
		this.selection = (SelectionI) config.get("selection");
		this.crossover = (CrossoverI) config.get("crossover");
		
		this.initialization = (TreeInitializerEnum) config.get("initialization");
		// Not Used
		// this.observer = (Observer) config.get("observer");
		
		if (this.mold.getFunction().isMaximization())
			this.comparator = new ChromosomeComparatorMax();
		else
			this.comparator = new ChromosomeComparatorMin();
		
		this.population = new ArrayList<>();
		elitism = new ArrayList<>();
		this.generationAverage = new double[GENERATION_AMOUNT];
		this.generationLeaders = new double[GENERATION_AMOUNT];
		generationsAbsoluteLeaders = new double[GENERATION_AMOUNT];
		selectivePressure = new double[GENERATION_AMOUNT];
		this.intergenerationLeader = null;
	}
	
	public void run() {
		initilize();
		basicEvaluation();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			extractElitism();
			select();
			cross();
			mutate();
			evaluate(i);
			insertElitism();
		}
	}
	
	private void insertElitism() {
		population.sort(comparator);
		for (int i = 0; i < ELITISM_AMOUNT; i++)
			population.remove(population.size() - 1);
		for (int i = 0; i < ELITISM_AMOUNT; i++)
			population.add(elitism.get(i));
	}

	private void extractElitism() {
		population.sort(comparator);
		elitism.clear();
		for (int i = 0; i < ELITISM_AMOUNT; i++)
			elitism.add(population.get(i).copy());
	}
	
	private void initilize() {
		population = Initializer.act(mold.getFunction().getGenType(), POPULATION_AMOUNT, mold, mold.getMaxHeigth() , initialization);
	}
	
	private void select() {
		List<ChromosomeI> newPopulation = selection.act(population);
		population.clear();
		for (ChromosomeI chromosome : newPopulation)
			population.add(chromosome.copy());
	}
	
	private void cross() {
		List<ChromosomeI> newPopulation = crossover.act(population);
		population.clear();
		for (ChromosomeI chromosome : newPopulation)
			population.add(chromosome.copy());
	}
	
	private void mutate() {
		List<ChromosomeI> newPopulation = PopulationMutation.act(population);
		population.clear();
		for (ChromosomeI chromosome : newPopulation)
			population.add(chromosome.copy());
	}
	
	private void basicEvaluation() {
		if (population.size() >= 0) {
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluateValue();
			double k = Covariance.calculate(population) / Variance.calculate(population);
			if (Double.isNaN(k))
				k = 0;
			mold.setK(k);
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluateFitness();
			ChromosomeI leader = population.get(0).copy();
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if (chromosome.getFunctionValue() < leader.getFunctionValue())
					leader = chromosome.copy();
			}
			// positivizeFitness();
		}
	}

	private void evaluate(int generation) {
		if (population.size() >= 0) {
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluateValue();
			double k = Covariance.calculate(population) / Variance.calculate(population);
			if (Double.isNaN(k))
				k = 0;
			mold.setK(k);
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluateFitness();
			ChromosomeI leader = population.get(0).copy();
			double fitnessSum = 0;
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if (chromosome.getFunctionValue() < leader.getFunctionValue())
					leader = chromosome.copy();
				fitnessSum += chromosome.getFunctionValue();
			}
			// positivizeFitness();
			
			// Calculamos la media de la generación
			generationAverage[generation] = fitnessSum / population.size();
			//this.observer.updateGenerationAverage(generationAverage);
			
			// Añadimos el mejor cromosoma de la generación a la lista
			generationLeaders[generation] = leader.getFunctionValue();
			
			// this.observer.updateGenerationLeaders(generationLeaders);
			
			// Comprobamos si el mejor cromosoma de la generacion es el mejor global
			if (this.intergenerationLeader == null)
				intergenerationLeader = leader.copy();
			// this.observer.updateIntergenerationLeader(intergenerationLeader);
			else if ((leader.getFunctionValue() > intergenerationLeader.getFunctionValue()
					&& mold.getFunction().isMaximization()) ||
					(leader.getFunctionValue() < intergenerationLeader.getFunctionValue()
					&& !mold.getFunction().isMaximization())) {
				intergenerationLeader = leader.copy();
				// this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
			
			// Se añade el lider absoluto del momento
			if (generation < 1 || (generationsAbsoluteLeaders[generation-1] < leader.getFunctionValue() &&
					mold.getFunction().isMaximization()) || (generationsAbsoluteLeaders[generation-1] > leader.getFunctionValue()
							&& !mold.getFunction().isMaximization())) {
				generationsAbsoluteLeaders[generation] = leader.getFunctionValue();
			}
			else
				generationsAbsoluteLeaders[generation] = generationsAbsoluteLeaders[generation-1];
			
			selectivePressure[generation] = generationLeaders[generation] / generationAverage[generation];
		}
	}
	
	public double[] getGenerationAverage() { return generationAverage; }
	public double[] getGenerationLeaders() { return generationLeaders; }
	public double[] getAbsoluteLeaders() { return generationsAbsoluteLeaders; }
	public double[] getSelectivePressure() { return selectivePressure; }
	
	public String getBestChromosomeToString() {
		return "El mejor cromosoma tiene un valor de " +
				(Math.round(intergenerationLeader.getFunctionValue() * 10000.0) / 10000.0) +
				" con la expresión:\n" + addLines(replace(intergenerationLeader.pretty())) +
				"\ny con el recorrido en inorden:\n" + addLines(intergenerationLeader.toString());
	}
	
	private String replace(String text) {
		return text.replaceAll("- -", "+");
	}
	
	private String addLines(String text) {
		String newText = "";
		int position = 0;
		final int MAX_LENGTH = 110;
		int i = 1;
		while (i * MAX_LENGTH < text.length()) {
			 newText += text.substring(position, position + MAX_LENGTH) + '\n';
			 position += MAX_LENGTH;
			 i++;
		}
		newText += text.substring(position);
		return newText;
	}
	
	public double[] getIdealFunction() {
		return mold.getFunction().getIdealFunction();
	}
	
	public double[] getbestFunction() {
		return mold.getFunction().getFunction(intergenerationLeader);
	}
	
	public double[] getXValues() {
		return ((AdaptationFunction) mold.getFunction()).getXValues();
	}
}
