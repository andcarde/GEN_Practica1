package graphic;

public interface RequestMaker {

	// Integer
	String getPopulationAmount();
	
	// Integer
	String getGenerationAmount();
	
	// Integer
	String getCrossoverPercentage();
	
	// Integer
	String getMutationPercentage();
	
	// Double
	String getPrecision();
	
	String getSelectionMethod();
	
	String getCrossoverMethod();
	
	String getMutationMethod();
	
	// Double
	String getElitismPercentage();
	
	String getFitnessFunction();
	
	// Integer
	String getContestantsAmount();
	
	// Double
	String getChampionPercentage();
	
	// Integer
	String getFuction4Dimension();
}