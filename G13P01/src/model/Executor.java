package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private List<Double> generationAverage;
	// List of the best individuals of each promotion
	private List<Double> generationLeaders;
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
		this.generationAverage = new ArrayList<>();
		this.generationLeaders = new ArrayList<>();
		this.intergenerationLeader = null;
	}
	
	public void run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			select();
			cross();
			mutate();
			evaluate();
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

	private void evaluate() {
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
			
			// Calculamos la media de la generación
			generationAverage.add(fitnessSum / population.size());
			this.observer.updateGenerationAverage(generationAverage);
			
			// Añadimos el mejor cromosoma de la generación a la lista
			generationLeaders.add(leader.getValue());
			this.observer.updateGenerationLeaders(generationLeaders);
			
			// Comprobamos si el mejor cromosoma de la generacion es el mejor global
			if (leader.getValue() > intergenerationLeader.getValue()) {
				intergenerationLeader = leader;
				this.observer.updateIntergenerationLeader(intergenerationLeader);
			}
		}
	}
}
