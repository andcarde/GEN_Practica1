package control;

import java.util.ArrayList;
import java.util.List;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;

public class Request {
	
	private List<String> errors;
	private Integer populationAmount;
	private Integer generationAmount;
	private Double crossoverProbability;
	private Double mutationProbability;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	private Integer elitismRate;
	private FitnessFunction fitnessFunction;
	
	public Request(RequestMaker requestMaker) throws InvalidInputException {
		this.errors = new ArrayList<>();
		initializeAttributes(requestMaker);
		if (!this.errors.isEmpty())
			throw new InvalidInputException(errors);
		checkValidity();
		if (!this.errors.isEmpty())
			throw new InvalidInputException(errors);
	}
	
	private void initializeAttributes(RequestMaker requestMaker) {
		try {
			this.populationAmount = Integer.valueOf(requestMaker.getPopulationAmount());
		} catch (NumberFormatException nfe) {
			this.errors.add("The population amount must be an integer.");
		}
		try {
			this.generationAmount = Integer.valueOf(requestMaker.getGenerationAmount());
		} catch (NumberFormatException nfe) {
			this.errors.add("The generation amount must be an integer.");
		}
		try {
			this.crossoverProbability = Double.valueOf(requestMaker.getCrossoverPercentage()) / 100;
		} catch (NumberFormatException nfe) {
			this.errors.add("The crossover probability must be a rational number.");
		}
		try {
			this.mutationProbability = Double.valueOf(requestMaker.getMutationPercentage()) / 100;
		} catch (NumberFormatException nfe) {
			this.errors.add("The mutation probability must be a rational number.");
		}
		
		this.selectionMethod = SelectionMethod.valueOf(SelectionMethod.class, requestMaker.getSelectionMethod());
		
			
		this.crossoverMethod = CrossoverMethod.valueOf(CrossoverMethod.class, requestMaker.getCrossoverMethod());
		this.mutationMethod = MutationMethod.valueOf(MutationMethod.class, requestMaker.getMutationMethod());
		try {
			this.elitismRate = (int) Double.parseDouble(requestMaker.getElitismPercentage());
		} catch (NumberFormatException nfe) {
			this.errors.add("The elitism rate must be a rational number.");
		}
		this.fitnessFunction = FitnessFunction.valueOf(FitnessFunction.class, requestMaker.getFitnessFunction());
		
	}
	
	public void checkValidity() {
		if (this.populationAmount <= 0)
			errors.add("The population amount must be a positive integer.");
		if (this.generationAmount <= 0)
			errors.add("The generations number must be a positive integer.");
		if (this.crossoverProbability < 0 || this.crossoverProbability > 1)
			errors.add("The crossover probability must be between 0 and 100 (both included).");
		if (this.mutationProbability < 0 || this.mutationProbability > 1)
			errors.add("The mutation probability must be between 0 and 100 (both included).");
		if (this.elitismRate < 0 || this.elitismRate > 100)
			errors.add("The elitism rate must be between 0 and 100 (both included).");
		if (!Available.isCrossoverAvailable(fitnessFunction, crossoverMethod)) {
			errors.add("The crossover method " + this.crossoverMethod.name() +
					" is not available for the function " + fitnessFunction.name());
		}
		if (!Available.isMutationAvailable(fitnessFunction, mutationMethod)) {
			errors.add("The mutation method " + this.mutationMethod.name() +
					" is not available for the function " + fitnessFunction.name());
		}
	}
	
	public Integer getPopulationAmount() {
		return populationAmount;
	}

	public Integer getGenerationAmount() {
		return generationAmount;
	}

	public Double getCrossoverProbability() {
		return crossoverProbability;
	}

	public Double getMutationProbability() {
		return mutationProbability;
	}

	public SelectionMethod getSelectionMethod() {
		return selectionMethod;
	}
	
	public CrossoverMethod getCrossoverMethod() {
		return crossoverMethod;
	}

	public MutationMethod getMutationMethod() {
		return mutationMethod;
	}

	public Integer getElitismProbability() {
		return elitismRate;
	}

	public FitnessFunction getFitnessFunction() {
		return fitnessFunction;
	}

}
