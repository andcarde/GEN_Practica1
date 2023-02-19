package model.chromosome;

import java.util.Random;

public abstract class Chromosome<T> {
	protected T[] chromosome;
	protected int[] geneSize;
	protected double[] min, max;
	protected int totalSize;
	
	public abstract int tamGen(double err, double min, double max);
	protected abstract double getValor();
	public double getFitness() { return getValor();}
	public abstract void mutate(double tasaMutacion);

}
