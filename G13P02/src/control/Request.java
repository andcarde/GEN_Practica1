package control;

import java.util.ArrayList;
import java.util.List;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;

public class Request {
	
	private List<String> errors;
	private Integer populationAmount;
	private Integer generationAmount;
	private Integer maxDepth;
	private Double crossoverProbability;
	private Double mutationProbability;
	private Double truncation = 0.0;
	private TreeInitializerEnum initializationMethod;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	private Integer elitismRate;
	private FitnessFunction fitnessFunction;
	private TournamentRequest tournamentRequest;
	private boolean bloating;
	private Long seed;
	
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
		this.bloating = requestMaker.isBloatingActive();
		try {
			maxDepth = requestMaker.getMaxDepth();
		} catch (NumberFormatException nfe) {
			this.errors.add("The population amount must be an integer.");
		}
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
			String seedString = requestMaker.getSeed();
			if (!seedString.equals(""))
				this.seed = Long.valueOf(seedString);
		} catch (NumberFormatException nfe) {
			this.errors.add("The input seed not match as a the Java's Long type");
		}
		this.initializationMethod = TreeInitializerEnum.valueOf(TreeInitializerEnum.class, requestMaker.getInitializationMethod());
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
			this.errors.add("The elitism rate must be a rational number.");
		}
		this.fitnessFunction = FitnessFunction.valueOf(FitnessFunction.class, requestMaker.getFitnessFunction());
	}
	
	public void checkValidity() {
		if (maxDepth < 2 || maxDepth > 5) errors.add("The maximun depth must be between 2 adn 5 (both included).");
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

	public TreeInitializerEnum getInitalizationMethod() {
		return initializationMethod;
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

	public TournamentRequest getTournamentRequest() {
		return tournamentRequest;
	}

	public Double getTruncationAmount() {
		return truncation;
	}
	
	public boolean isBloatingActive() {
		return bloating;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public Long getSeed() {
		return seed;
	}
}
