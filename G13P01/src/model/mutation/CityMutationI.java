package model.mutation;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;

public interface CityMutationI extends MutationI {
	
	List<GenI> act(ChromosomeI chromosome);
}
