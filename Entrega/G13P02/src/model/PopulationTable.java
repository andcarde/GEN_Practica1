package model;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;

public class PopulationTable {

	private Integer populationAmount;
	private Double fitnessSumatory;
	private List<Double> fitness;
	private List<Double> punctuation;
	private List<Double> accumulated;
	
	public PopulationTable(List<ChromosomeI> population) {
		this.populationAmount = population.size();
		this.fitness = new ArrayList<>();
		this.punctuation = new ArrayList<>();
		this.accumulated = new ArrayList<>();
		initTable(population);
	}
	
	private void initTable(List<ChromosomeI> population) {
		for (int i = 0; i < populationAmount; i++)
			this.fitness.add(population.get(i).getAlterValue());
		this.fitnessSumatory = 0.0;
		for (int i = 0; i < populationAmount; i++)
			this.fitnessSumatory += this.fitness.get(i);
		double accumulatedPunctuation = 0.0;
		double actualPunctuation;
		for (int i = 0; i < populationAmount; i++) {
			actualPunctuation = this.fitness.get(i) / this.fitnessSumatory;
			accumulatedPunctuation += actualPunctuation;
			this.punctuation.add(actualPunctuation);
			this.accumulated.add(accumulatedPunctuation);
		}
	}
	
	public List<Double> getFitness() {
		return this.fitness;
	}
	
	public List<Double> getPunctuation() {
		return this.punctuation;
	}
	
	public List<Double> getAccumulated() {
		return this.accumulated;
	}

	public Integer getAmount() {
		return this.populationAmount;
	}

	public List<Double> getPunctuation(List<Double> fitness2) {
		List<Double> ret = new ArrayList<>();
		double total = 0.0;
		for (int i = 0; i < fitness2.size(); i++)
			total += fitness2.get(i);
		for (int i = 0; i < fitness2.size(); i++)
			ret.add(fitness2.get(i)/total);
		return ret;
	}
}
