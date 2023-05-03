package control;

public interface RequestMaker {

	// Integer
	String getPopulationAmount();
	
	// Integer
	String getGenerationAmount();
	
	// Integer
	String getCrossoverPercentage();
	
	// Integer
	String getMutationPercentage();
	
	String getGenType();
	
	String getInitializationMethod();
	
	String getSelectionMethod();
	
	String getCrossoverMethod();
	
	String getMutationMethod();
	
	// Double
	String getElitismPercentage();
	
	String getFitnessFunction();
	
	String getContestantsAmount();
		
	// Double
	String getChampionPercentage();
		
	// String
	String getTruncationPercentage();
	
	boolean isBloatingActive();

	String getMaxDepth();

	String getSeed();

	String getWraps();
	
	
}