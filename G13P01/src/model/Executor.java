package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graphic.Observer;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeComparator;
import model.chromosome.ChromosomeI;
import model.crossover.CrossoverI;
import model.mutation.MutationI;
import model.selection.SelectionI;

public class Executor {

	// MODEL CONSTRAINTS -----------------------------------------------
	private final Integer GENERATION_AMOUNT;
	private final Integer POPULATION_AMOUNT;
	private final Integer ELITISM_AMOUNT;
	private final MoldI mold;
	private final SelectionI selection;
	private final CrossoverI crossover;
	private final MutationI mutation;
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
	// ------------------------------------------------------------------
	
	private Observer observer;
	
	public Executor(Map<String, Object> config) {
		this.GENERATION_AMOUNT = (Integer) config.get("generation_amount");
		this.POPULATION_AMOUNT = (Integer) config.get("population_amount");
		this.ELITISM_AMOUNT = (Integer) config.get("elitism_amount") * POPULATION_AMOUNT / 100;
		this.mold = (MoldI) config.get("mold");
		this.selection = (SelectionI) config.get("selection");
		this.crossover = (CrossoverI) config.get("crossover");
		this.mutation = (MutationI) config.get("mutation");
		
		this.observer = (Observer) config.get("observer");
		
		this.population = new ArrayList<>();
		elitism = new ArrayList<>();
		this.generationAverage = new double[GENERATION_AMOUNT];
		this.generationLeaders = new double[GENERATION_AMOUNT];
		generationsAbsoluteLeaders = new double[GENERATION_AMOUNT];
		this.intergenerationLeader = null;
	}
	
	public void run() {
		initilize();
		basicEvaluation();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			population.sort(new ChromosomeComparator());
			extractElitism();
			select();
			cross();
			mutate();
			population.sort(new ChromosomeComparator());
			insertElitism();
			evaluate(i);
		}
	}
	
	private void insertElitism() {
		for (int i = 0; i < elitism.size(); i++) {
			population.remove(population.size()-1);
			population.add(elitism.get(i));
			population.sort(new ChromosomeComparator());
		}
	}

	private void extractElitism() {
		elitism.clear();
		for (int i = 0; i < ELITISM_AMOUNT; i++) {
			elitism.add(population.get(i).copy());
		}
		
	}

	private void initilize() {
		for (int i = 0; i < POPULATION_AMOUNT; i++)
			population.add(new Chromosome(mold));
		for (ChromosomeI chromosome : population)
			chromosome.initialize();
	}
	
	private void select() {
		population = selection.act(population);
	}
	
	private void cross() {
		population = crossover.act(population);
	}
	
	private void mutate() {
		for (ChromosomeI chromosome : population)
			this.mutation.act(chromosome);
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
			positivizeFitness();
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
				if (chromosome.getValue() > leader.getValue())
					leader = chromosome.copy();
				fitnessSum += chromosome.getValue();
			}
			positivizeFitness();
			
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
			else if (leader.getValue() > intergenerationLeader.getValue()) {
				intergenerationLeader = leader.copy();
				//this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
			
			//Se añade el lider absoluto del momento
			if (generation < 1 || generationsAbsoluteLeaders[generation-1] < leader.getValue()) {
				generationsAbsoluteLeaders[generation] = leader.getValue();
			}
			else generationsAbsoluteLeaders[generation] = generationsAbsoluteLeaders[generation-1];
		}
	}
	
	private void positivizeFitness() {
		double toSum = 0.0;
		for (ChromosomeI chromosome : this.population) {
			if (chromosome.getValue() < toSum)
				toSum = chromosome.getValue();
		}
		toSum *= -1;
		for (ChromosomeI chromosome : this.population)
			chromosome.displace(toSum);
	}

	public double[] getGenerationAverage() { return generationAverage; }
	public double[] getGenerationLeaders() { return generationLeaders; }
	public double[] getAbsoluteLeaders() { return generationsAbsoluteLeaders; }

	public String getBestChromosomeToString() {
		return "El mejor cromosoma tiene un valor de " + intergenerationLeader.getValue()
			+ " con los parámetros: " + intergenerationLeader.getGenesToString();
	}
}
