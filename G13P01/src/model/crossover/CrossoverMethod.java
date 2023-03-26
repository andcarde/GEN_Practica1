package model.crossover;

public enum CrossoverMethod {

	// Binary or Real Chromosome
	ONE_POINT, UNIFORM, ARITHMETIC, BLX_ALPHA,
	// City Chromosome
	PMX, OX, POX, PPOX, CX, ERX, CO, AO;
	
	/* PMX: Partial Pairing
	 * OX: Order
	 * POX: Prioritary Order
	 * PPOX: Prioritary Position Order
	 * CX: Cycles
	 * ERX: Route Recombination
	 * CO: Ordinal Codification
	 * AO: Alternative Order Crossover (Método propio)
	 * */
}
