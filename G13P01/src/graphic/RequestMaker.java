package graphic;

public interface RequestMaker {

	Integer getPopulationAmount();

	Integer getGenerationAmount();

	Integer getCrossoverPercentage();

	Integer getMutationPercentage();

	Double getPrecision();

	String getSelectionMethod();

	String getTournamentMode();
	
	String getCrossoverMethod();

	String getMutationMethod();
	
	Integer getElitismPercentage();

	String getFitnessFunction();

	Integer getContestantsAmount();

	Integer getChampionProbability();

	Integer getFuction4Dimension();
}
