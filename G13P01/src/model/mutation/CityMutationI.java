package model.mutation;

import java.util.List;

import model.chromosome.GenI;

public interface CityMutationI extends MutationI {
	
	void act(List<GenI> genes);
}
