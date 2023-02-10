package launcher;

public class Configuration {

	// Somos el grupo 13
	
	POPULATION_AMOUNT = 100;
	GENERATION_AMOUNT = 100;
	CRUCE_PROBABILITY = 0.6
	MUTATION_PROBABILITY = 0.05
	PRESICION = 0.001
	SELECTION_METHOD = SelectionType.;
	CRCE_METHOD = ;
	MUTATION_METHOD = ;
	ELITISM_PROBABILITY = ;
	D_VALUE_FUNCTION4 = ;
	
	private Map map
	
	public Configuration() {
		
	}
	
	public Integer getPopulationAmount() {
		return this.populationAmount;
	}
	
	public Integer getGenerationAmount() {
		return this.generationAmount;
	}
	
	public Double getCruceProbability() {
		return this.cruveProbability;
	}
	
	public Double getSolutionPrecition() {
		return this.solutionPresition();
	}
	
	public SelectionMethod getSelectionMethod() {
		return this.solutionMethod;
	}
}
