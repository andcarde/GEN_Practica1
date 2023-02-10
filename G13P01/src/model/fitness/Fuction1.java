package model.fitness;

public class Fuction1 implements Fitness {

	/* La primera funci�n se llama de calibraci�n porque es simple y por lo tanto debe converger de forma m�s simple.
	 * */
	
	@Override
	public Double getValue(Input input) {
		Double phenotype = 21.5;
		phenotype += input.get("x1") * Math.sin(4 * Math.PI * input.get("x1"));
		phenotype += input.get("x2") * Math.sin(20 * Math.PI * input.get("x2"));
		return phenotype;
	}
}
