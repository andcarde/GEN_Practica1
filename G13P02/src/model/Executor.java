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
	/*private final GenType genType;
	private final MutationI mutation;*/
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
		List<ChromosomeI> ret = selection.act(population);
		population.clear();
		for (ChromosomeI c : ret) {
			population.add(c.copy());
		}
	}
	
	private void cross() {
		List<ChromosomeI> ret = crossover.act(population);
		population.clear();
		for (ChromosomeI c : ret) {
			population.add(c.copy());
		}
	}
	
	private void mutate() {
		for (ChromosomeI chromosome : population)
			chromosome.mutate();
	}
	
	private void basicEvaluation() {
		if (population.size() >= 0) {
			double k = Covariance.calculate(population) / Variance.calculate(population);
			if (Double.isNaN(k)) k = 0;
			mold.setK(k);
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluate();
			ChromosomeI leader = population.get(0);
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if ((chromosome.getValue() > leader.getValue() && mold.getFunction().isMaximization())
						|| (chromosome.getValue() < leader.getValue() && !mold.getFunction().isMaximization()))
					leader = chromosome.copy();
			}
			//positivizeFitness();
		}
	}

	private void evaluate(int generation) {
		if (population.size() >= 0) {
			double k = Covariance.calculate(population) / Variance.calculate(population);
			if (Double.isNaN(k)) k = 0;
			mold.setK(k);
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluate();
			ChromosomeI leader = population.get(0);
			double fitnessSum = 0;
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if ((chromosome.getValue() > leader.getValue() && mold.getFunction().isMaximization())
						|| (chromosome.getValue() < leader.getValue() && !mold.getFunction().isMaximization()))
					leader = chromosome.copy();
				fitnessSum += chromosome.getValue();
			}
			//positivizeFitness();
			
			// Calculamos la media de la generaci�n
			generationAverage[generation] = fitnessSum / population.size();
			//this.observer.updateGenerationAverage(generationAverage);
			
			// A�adimos el mejor cromosoma de la generaci�n a la lista
			generationLeaders[generation] = leader.getValue();
			
			//this.observer.updateGenerationLeaders(generationLeaders);
			
			// Comprobamos si el mejor cromosoma de la generacion es el mejor global
			if (this.intergenerationLeader == null)
				intergenerationLeader = leader.copy();
			//this.observer.updateIntergenerationLeader(intergenerationLeader);
			else if ((leader.getValue() > intergenerationLeader.getValue() && mold.getFunction().isMaximization()) ||
					(leader.getValue() < intergenerationLeader.getValue() && !mold.getFunction().isMaximization())) {
				intergenerationLeader = leader.copy();
				//this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
			
			//Se a�ade el lider absoluto del momento
			if (generation < 1 || (generationsAbsoluteLeaders[generation-1] < leader.getValue() &&
					mold.getFunction().isMaximization()) || (generationsAbsoluteLeaders[generation-1] > leader.getValue()
							&& !mold.getFunction().isMaximization())) {
				generationsAbsoluteLeaders[generation] = leader.getValue();
			}
			else generationsAbsoluteLeaders[generation] = generationsAbsoluteLeaders[generation-1];
			
			selectivePressure[generation] = generationLeaders[generation] / generationAverage[generation];
		}
	}
	
	public List<ChromosomeI> getPopulation() {
		return population;
	}

	public double[] getGenerationAverage() { return generationAverage; }
	public double[] getGenerationLeaders() { return generationLeaders; }
	public double[] getAbsoluteLeaders() { return generationsAbsoluteLeaders; }
	public double[] getSelectivePressure() { return selectivePressure; }

	public String getBestChromosomeToString() {
		return "El mejor cromosoma tiene un valor de " + intergenerationLeader.getValue()
			+ " con los parametros: \n\r" + intergenerationLeader.getGenesToString();
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
