package model.mutation;

public enum MutationMethod {
	
	// The next mutation methods can only perform mutation on a Tree Chromosome
	TERMINAL, FUNCTIONAL, PERMUTATION, HOIST,
	
	// Affect the binary gene
	REAL;
}
