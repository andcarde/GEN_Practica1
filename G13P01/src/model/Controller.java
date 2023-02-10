package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.chromosome.Chromosome;
import model.chromosome.ChromosomeFunction1;
import model.fitness.Fitness;

public class Controller {

	private final Integer GENERATION_AMOUNT;
	private final Integer POBLATION_AMOUNT;
	private final double ERROR_AMOUNT;
	private final SelectionType SELECTION_TYPE;
	private List<Chromosome> population;
	
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

	public Map<Chromosome, Double> evaluate() {
		for (Chromosome chromosome : population ) {
				Double value = chromosome.getFitness();
		}
	}
}
