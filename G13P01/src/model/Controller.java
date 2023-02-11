package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.chromosome.Chromosome;
import model.chromosome.ChromosomeFunction1;
import model.fitness.Fitness;

public class Controller {

	private final Integer GENERATION_AMOUNT;
	private final Integer POBLATION_AMOUNT;
	private final double MUTATION;
	private final double ERROR_AMOUNT;
	private final SelectionType SELECTION_TYPE;
	private List<Chromosome> population;
	private Random rand;
	
	public Controller(SelectionType selection_TYPE, Integer poblation_AMOUNT, Integer generation_AMOUNT, double error_AMOUNT, double mutation) {
		this.GENERATION_AMOUNT = generation_AMOUNT;
		this.POBLATION_AMOUNT = poblation_AMOUNT;
		this.MUTATION = mutation;
		this.ERROR_AMOUNT = error_AMOUNT;
		this.SELECTION_TYPE = selection_TYPE;
	}
	
	public void run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			selection();
			cruce();
			mutation();
			evaluate();
		}
	}
	
	private void initilize() {
		population = new ArrayList<Chromosome>();
		for (int i = 0; i < POBLATION_AMOUNT; i++) {
			Chromosome aux = new ChromosomeFunction1(ERROR_AMOUNT);
			population.add(aux);
		}
		
	}
	
	private void mutation() {
		for (Chromosome chromosome : population) {
			chromosome.mutate(MUTATION);
		}
	}

	public Chromosome evaluate() {
		Chromosome bestChr = null;
		for (Chromosome chromosome : population ) {
				double value = chromosome.getFitness();
				if (bestChr == null || value > bestChr.getFitness())
					bestChr = chromosome;
		}
		//Queda interactuar con la vista
		return bestChr;
	}
}
