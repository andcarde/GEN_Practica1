package model;

import java.util.List;
import java.util.Map;

import model.fitness.Fitness;

public class Controller {

	private final Integer GENERATION_AMOUNT;
	private final Integer POBLATION_AMOUNT;
	private final SelectionType SELECTION_TYPE;
	
	public run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			selection();
			cruce();
			mutation();
			evaluate();
		}
	}
	
	public Map<Chromosome, Double> evaluate(List<Chromosome> population, Fitness function1) {
		for (Chromosome chromosome : population ) {
			for (int i = 0; i < chromosome.getGenAmount(); i++) {
				Double value = chromosome.translate();
				
			}
		}
	}
}
