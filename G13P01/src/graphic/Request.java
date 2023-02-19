package graphic;

import java.util.ArrayList;
import java.util.List;

import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;
import model.selection.TournamentMode;

public class Request {

	private Integer populationAmount;
	private Integer generationAmount;
	private Double crossoverProbability;
	private Double mutationProbability;
	private Double precision;
	private SelectionMethod selectionMethod;
	private CrossoverMethod crossoverMethod;
	private MutationMethod mutationMethod;
	private Double elitismProbability;
	private FitnessFunction fitnessFunction;
	private Integer fuction4Dimension;
	private TournamentRequest tournamentRequest;
	
	Request(RequestMaker requestMaker) throws InvalidInputException {
		this.populationAmount = requestMaker.getPopulationAmount();
		this.generationAmount = requestMaker.getGenerationAmount();
		this.crossoverProbability = (double) requestMaker.getCrossoverPercentage() / 100;
		this.mutationProbability = (double)  requestMaker.getMutationPercentage() / 100;
		this.precision = requestMaker.getPrecision();
		this.selectionMethod = SelectionMethod.valueOf(SelectionMethod.class, requestMaker.getSelectionMethod());
		if (this.selectionMethod == SelectionMethod.TOURNAMENT) {
			Integer contestantsAmount = requestMaker.getContestantsAmount();
			TournamentMode tournamentMode = TournamentMode.valueOf(TournamentMode.class, requestMaker.getTournamentMode());
			tournamentRequest = new TournamentRequest(tournamentMode, contestantsAmount);
			if (tournamentMode == TournamentMode.PROBABILIST) {
				Integer championProbability = requestMaker.getChampionProbability();
				tournamentRequest.setChampionProbability(championProbability);
			}
		}
		this.crossoverMethod = CrossoverMethod.valueOf(CrossoverMethod.class, requestMaker.getCrossoverMethod());
		this.mutationMethod = MutationMethod.valueOf(MutationMethod.class, requestMaker.getMutationMethod());
		this.elitismProbability = (double) requestMaker.getElitismPercentage() / 100;
		this.fitnessFunction = FitnessFunction.valueOf(FitnessFunction.class, requestMaker.getFitnessFunction());
		if (this.fitnessFunction == FitnessFunction.FUNCTION4)
			this.fuction4Dimension = requestMaker.getFuction4Dimension();
		List<String> errors = checkValidity();
		if (!errors.isEmpty())
			throw new InvalidInputException(errors);
	}
	
	public List<String> checkValidity() {
		List<String> errors = new ArrayList<>();
		if (tournamentRequest.getContestantsAmount() > populationAmount)
			errors.add("The amount of contestants must be equal or lower than the population amount.");
		return errors;
			
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

	public Double getElitismProbability() {
		return elitismProbability;
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
}
