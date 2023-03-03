package model.mutation;

public interface MutationI {

	void act<T extends GenI>(T gen);
}
