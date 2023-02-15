package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	// Lista de los mejores individuos de cada promoci�n
	private List<Chromosome> tier;
	*/
	private final Integer GENERATION_AMOUNT;
	private final Integer POPULATION_AMOUNT;
	private final double PRECISION;
	private MutationI mutation;
	private List<ChromosomeI> population;
	// Lista de los mejores individuos de cada promoci�n
	private List<Double> generationAverage;
	private List<Double> generationBest;
	private ChromosomeI intergenerationBest;
	private final CrossoverI crossover;
	private final SelectionMethod selection;
	
	public Executor(Map<String, Object> config) {
		this.GENERATION_AMOUNT = (Integer) config.get("generation_amount");
		this.POPULATION_AMOUNT = (Integer) config.get("population_amount");
		this.mutation = (MutationI) config.get("mutation");
		this.selection = (SelectionMethod) config.get("selection");
		this.crossover = (CrossoverI) config.get("crossover");
		PRECISION = (double) config.get("precision");	
	}
	
	public void run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			population = selection.act(population);
			population = crossover.act(population);
			mutation();
			evaluation();
		}
	}
	
	private void initilize() {
		population = new ArrayList<ChromosomeI>();
		for (int i = 0; i < POPULATION_AMOUNT; i++) {
			ChromosomeI aux = new ChromosomeFunction1(PRECISION);
			population.add(aux);
		}
	}
	
	private void mutation() {
		for (ChromosomeI chromosome : population)
			chromosome = mutation.act(chromosome);
	}
	
	public void evaluation() {
		ChromosomeI cur_chromosome = evaluate();
		if (cur_chromosome.getFitness() > intergenerationBest.getFitness())
			intergenerationBest = cur_chromosome; //Comprobamos si el mejor cromosoma de la generacion es el mejor global
		generationBest.add(cur_chromosome.getFitness()); //Añadimos el mejor cromosoma de la generacion a la lista
	}

	public ChromosomeI evaluate() {
		ChromosomeI bestChr = null;
		double sumFit = 0;
		for (ChromosomeI chromosome : population ) {
				double value = chromosome.getFitness();
				if (bestChr == null || value > bestChr.getFitness())
					bestChr = chromosome;
				sumFit += value;
		}
		generationAverage.add(sumFit/population.size()); //Calculamos la media de la generacion
		
		return bestChr;
	}
}
