package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Chromosome;
import model.chromosome.ChromosomeFunction1;
import model.chromosome.ChromosomeI;
import model.crossover.Crossover;
import model.crossover.CrossoverI;
import model.fitness.Fitness;
import model.mutation.MutationI;
import model.selection.SelectionMethod;

public class Executor {

	/*
	private final double MUTATION;
	private final double ERROR_AMOUNT;
	private final SelectionType SELECTION_TYPE;
	private List<Chromosome> population;
	// Lista de los mejores individuos de cada promoción
	private List<Chromosome> tier;
	*/
	private final Integer GENERATION_AMOUNT;
	private final Integer POPULATION_AMOUNT;
	private MutationI mutation;
	private List<ChromosomeI> population;
	// Lista de los mejores individuos de cada promoción
	private List<Double> generationAverage;
	private List<Double> generationBest;
	private Chromosome intergenerationBest;
	private final CrossoverI crossover;
	private final SelectionMethod selection;
	
	public Executor(Map<String, Object> config, SelectionType selection_TYPE, Integer poblation_AMOUNT, Integer generation_AMOUNT, double error_AMOUNT, double mutation) {
		this.GENERATION_AMOUNT = (Integer) config.get("generation_amount");
		this.POPULATION_AMOUNT = (Integer) config.get("population_amount");
		this.mutation = (MutationI) config.get("mutation");
		this.selection = (SelectionMethod) config.get("selection");
		this.crossover = (CrossoverI) config.get("crossover");
		//this.ERROR_AMOUNT = error_AMOUNT;
	}
	
	public void run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			population = selection.act(population);
			population = crossover.act(population);
			mutation();
			population = mutation.act(chromosome);
			evaluate();
		}
	}
	
	private void initilize() {
		population = new ArrayList<Chromosome>();
		for (int i = 0; i < POPULATION_AMOUNT; i++) {
			Chromosome aux = new ChromosomeFunction1(ERROR_AMOUNT);
			population.add(aux);
		}
	}
	
	private void mutation() {
		for (ChromosomeI chromosome : population)
			chromosome = mutation.act(chromosome);
	}

	public Chromosome evaluate() {
		ChromosomeI bestChr = null;
		for (ChromosomeI chromosome : population ) {
				double value = chromosome.getFitness();
				if (bestChr == null || value > bestChr.getFitness())
					bestChr = chromosome;
		}
		//Queda interactuar con la vista
		return bestChr;
	}
}
