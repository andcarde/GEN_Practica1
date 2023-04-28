package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import model.chromosome.ChromosomeI;
import model.chromosome.practice3.TreeChromosome;
import model.crossover.CrossoverI;
import model.fitness.practice3.AdaptationFunction;
import model.initialization.practice3.TreePopulationInitializer;
import model.selection.SelectionI;

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
	private TreePopulationInitializer initialization;
	// ------------------------------------------------------------------
	
	private List<TreeChromosome> population;
	private List<TreeChromosome> elitism;
	
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
		/*this.genType = (GenType) config.get("gen_type");
		this.mutation = (MutationI) config.get("mutation");*/
		this.initialization = (TreePopulationInitializer) config.get("initialization");
		// Not Used
		// this.observer = (Observer) config.get("observer");
		
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
			population.sort(comparator);
			extractElitism();
			select();
			cross();
			mutate();
			population.sort(comparator);
			insertElitism();
			evaluate(i);
		}
	}
	
	private void insertElitism() {
		for (int i = 0; i < elitism.size(); i++) {
			population.remove(population.size()-1);
			population.add(elitism.get(i));
			population.sort(comparator);
		}
	}

	private void extractElitism() {
		elitism.clear();
		for (int i = 0; i < ELITISM_AMOUNT; i++) {
			elitism.add(population.get(i).copy());
		}
		
	}
	
	private void initilize() {
		population = initialization.initialize();
	}
	
	private void select() {
		List<ChromosomeI> ret = new ArrayList<>();
		for (ChromosomeI c : population) {
			ret.add(c);
		}
		ret = selection.act(ret);
		population.clear();
		for (ChromosomeI c : ret) {
			population.add((TreeChromosome) c);
		}
		
	}
	
	private void cross() {
		List<ChromosomeI> ret = new ArrayList<>();
		for (ChromosomeI c : population) {
			ret.add(c);
		}
		ret = crossover.act(ret);
		population.clear();
		for (ChromosomeI c : ret) {
			population.add((TreeChromosome) c);
		}
	}
	
	private void mutate() {
		for (ChromosomeI chromosome : population)
			chromosome.mutate();
	}
	
	private void basicEvaluation() {
		if (population.size() >= 0) {
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluate();
			ChromosomeI leader = population.get(0);
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if (chromosome.getValue() > leader.getValue())
					leader = chromosome;
			}
			//positivizeFitness();
		}
	}

	private void evaluate(int generation) {
		if (population.size() >= 0) {
			for (ChromosomeI chromosome : this.population)
				chromosome.evaluate();
			ChromosomeI leader = population.get(0);
			double fitnessSum = leader.getValue();
			for (int i = 0; i < population.size(); i++) {
				ChromosomeI chromosome = population.get(i);
				if ((chromosome.getValue() > leader.getValue() && mold.getFunction().isMaximization()) || (chromosome.getValue() < leader.getValue() && !mold.getFunction().isMaximization()))
					leader = chromosome.copy();
				fitnessSum += chromosome.getValue();
			}
			//positivizeFitness();
			
			// Calculamos la media de la generación
			generationAverage[generation] = fitnessSum / population.size();
			//this.observer.updateGenerationAverage(generationAverage);
			
			// Añadimos el mejor cromosoma de la generación a la lista
			generationLeaders[generation] = leader.getValue();
			
			//this.observer.updateGenerationLeaders(generationLeaders);
			
			// Comprobamos si el mejor cromosoma de la generacion es el mejor global
			if (this.intergenerationLeader == null)
				intergenerationLeader = leader.copy();
			//this.observer.updateIntergenerationLeader(intergenerationLeader);
			else if ((leader.getValue() > intergenerationLeader.getValue() && mold.getFunction().isMaximization()) || (leader.getValue() < intergenerationLeader.getValue() && !mold.getFunction().isMaximization())) {
				intergenerationLeader = leader.copy();
				//this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
			
			//Se añade el lider absoluto del momento
			if (generation < 1 || (generationsAbsoluteLeaders[generation-1] < leader.getValue() && mold.getFunction().isMaximization()) || (generationsAbsoluteLeaders[generation-1] > leader.getValue() && !mold.getFunction().isMaximization())) {
				generationsAbsoluteLeaders[generation] = leader.getValue();
			}
			else generationsAbsoluteLeaders[generation] = generationsAbsoluteLeaders[generation-1];
			
			selectivePressure[generation] = generationLeaders[generation] / generationAverage[generation];
		}
	}
	
	public List<TreeChromosome> getPopulation() {
		return population;
	}

	public double[] getGenerationAverage() { return generationAverage; }
	public double[] getGenerationLeaders() { return generationLeaders; }
	public double[] getAbsoluteLeaders() { return generationsAbsoluteLeaders; }
	public double[] getSelectivePressure() { return selectivePressure; }

	public String getBestChromosomeToString() {
		return "El mejor cromosoma tiene un valor de " + intergenerationLeader.getValue()
			+ " con los parámetros: \n\r" + intergenerationLeader.getGenesToString();
	}
	
	public double[] getIdealFunction() {
		return mold.getFunction().getIdealFunction();
	}

	public double[] getbestFunction() {
		return mold.getFunction().getFunction(intergenerationLeader.getRaiz());
	}
	
	public double[] getXValues() {
		return ((AdaptationFunction) mold.getFunction()).getXValues();
	}
}
