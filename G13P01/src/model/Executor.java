package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graphic.Controller;
import graphic.Observer;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.crossover.CrossoverI;
import model.mutation.MutationI;
import model.selection.Selection;

public class Executor {

	// MODEL CONSTRAINTS -----------------------------------------------
	private final Integer GENERATION_AMOUNT;
	private final Integer POPULATION_AMOUNT;
	private final MoldI mold;
	private final Selection selection;
	private final CrossoverI crossover;
	private final MutationI mutation;
	// ------------------------------------------------------------------
	
	private List<ChromosomeI> population;
	
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
		this.mold = (MoldI) config.get("mold");
		this.selection = (Selection) config.get("selection");
		this.crossover = (CrossoverI) config.get("crossover");
		this.mutation = (MutationI) config.get("mutation");
		
		this.observer = (Observer) config.get("observer");
		
		this.population = new ArrayList<>();
		this.generationAverage = new double[POPULATION_AMOUNT];
		this.generationLeaders = new double[POPULATION_AMOUNT];
		generationsAbsoluteLeaders = new double[POPULATION_AMOUNT];
		this.intergenerationLeader = null;
	}
	
	public void run() {
		initilize();
		basicEvaluation();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			select();
			cross();
			mutate();
			evaluate(i);
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
					leader = chromosome;
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
			if (intergenerationLeader == null || leader.getValue() > intergenerationLeader.getValue()) {
				intergenerationLeader = leader;
				//this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
			
			//Se añade el lider absoluto del momento
			generationsAbsoluteLeaders[generation] = leader.getValue();
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
