package launcher;

public class Configuration {

	// Somos el grupo 13
	
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
