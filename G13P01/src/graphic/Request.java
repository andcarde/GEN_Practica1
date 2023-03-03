package graphic;

import java.util.ArrayList;
import java.util.List;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;

public class Request {

	// TODO Falta: Renombrar a "generation amount" por "generations number"
	
	private List<String> errors;
	private Integer populationAmount;
	private Integer generationAmount;
	private Double crossoverProbability;
	private Double mutationProbability;
	private Double precision;
	private Double truncation = 0.0;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	private Integer elitismRate;
	private FitnessFunction fitnessFunction;
	private Integer fuction4Dimension;
	private TournamentRequest tournamentRequest;
	
	Request(RequestMaker requestMaker) throws InvalidInputException {
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
		try {
			this.precision = Double.valueOf(requestMaker.getPrecision());
		} catch (NumberFormatException nfe) {
			this.errors.add("The precision must be a rational number.");
		}
		this.selectionMethod = SelectionMethod.valueOf(SelectionMethod.class, requestMaker.getSelectionMethod());
		if (this.selectionMethod == SelectionMethod.DETERMINISTIC_TOURNAMENT ||
				this.selectionMethod == SelectionMethod.PROBABILISTIC_TOURNAMENT) {
			try {
				Integer contestantsAmount = Integer.valueOf(requestMaker.getContestantsAmount());
				this.tournamentRequest = new TournamentRequest(contestantsAmount);
			} catch (NumberFormatException nfe) {
				this.errors.add("The contestants amount must be an integer.");
			}
			if (this.selectionMethod  == SelectionMethod.PROBABILISTIC_TOURNAMENT) {
				try {
					Double championProbability = Double.valueOf(requestMaker.getChampionPercentage()) / 100;
					if (this.tournamentRequest != null)
						this.tournamentRequest.setChampionProbability(championProbability);
				} catch (NumberFormatException nfe) {
					this.errors.add("The champion probability must be a rational number.");
				}
			}
		}
		if (this.selectionMethod == SelectionMethod.TRUNCATION) {
			try {
				truncation = Double.valueOf(requestMaker.getTruncationPercentage())/100;
			} catch (NumberFormatException nfe) {
				this.errors.add("The truncation amount must be an integer.");
			}
		}		
		this.crossoverMethod = CrossoverMethod.valueOf(CrossoverMethod.class, requestMaker.getCrossoverMethod());
		this.mutationMethod = MutationMethod.valueOf(MutationMethod.class, requestMaker.getMutationMethod());
		try {
			this.elitismRate = (int) Double.parseDouble(requestMaker.getElitismPercentage());
		} catch (NumberFormatException nfe) {
			System.out.println(requestMaker.getElitismPercentage());
			this.errors.add("The elitism rate must be a rational number.");
		}
		this.fitnessFunction = FitnessFunction.valueOf(FitnessFunction.class, requestMaker.getFitnessFunction());
		if (this.fitnessFunction == FitnessFunction.FUNCTION4b)
			try {
				this.fuction4Dimension = Integer.valueOf(requestMaker.getFuction4Dimension());
			} catch (NumberFormatException nfe) {
				this.errors.add("The parameter d of function 4 must be an integer.");
			}
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
		if (this.precision <= 0)
			errors.add("The precision must be a positive rational number.");
		if (this.elitismRate < 0 || this.elitismRate > 100)
			errors.add("The elitism rate must be between 0 and 100 (both included).");
		if (this.fitnessFunction == FitnessFunction.FUNCTION4b || this.fitnessFunction == FitnessFunction.FUNCTION4a)
			if (this.fuction4Dimension <= 0)
				errors.add("The parameter d of function 4 must be a positive integer.");
		if (this.selectionMethod == SelectionMethod.DETERMINISTIC_TOURNAMENT ||
				this.selectionMethod == SelectionMethod.PROBABILISTIC_TOURNAMENT) {
			Integer contestantsAmount = this.tournamentRequest.getContestantsAmount();
			if (contestantsAmount <= 0)
				errors.add("The contestants amount must be a positive integer.");
			if (contestantsAmount > this.populationAmount)
				errors.add("The amount of contestants must be equal or lower than the population amount.");
			if (this.selectionMethod == SelectionMethod.PROBABILISTIC_TOURNAMENT) {
				Double championProbability = this.tournamentRequest.getChampionProbability();
				if (championProbability < 0 || championProbability > 1)
					errors.add("The champion probability must be between 0 and 100 (both included).");
			}
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

	public Double getPrecision() {
		return precision;
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

	public Integer getFuction4Dimension() {
		return fuction4Dimension;
	}

	public TournamentRequest getTournamentRequest() {
		return tournamentRequest;
	}

	public Double getTruncationAmount() {
		return truncation;
	}
}
