package model.fitness;

public interface Input<T> {

	public void put(String s, T value);
	public T get(String string);
}
