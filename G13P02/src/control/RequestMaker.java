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
	
	// Double
	
	String getSelectionMethod();
	
	String getCrossoverMethod();
	
	String getMutationMethod();
	
	// Double
	String getElitismPercentage();
	
	String getFitnessFunction();
	

}