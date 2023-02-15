package model.chromosome;

import java.util.List;
import java.util.Random;

public class ChromosomeFunction1 extends Chromosome<Boolean> implements ChromosomeI {
	
	private InterpreterI interpreter;
	
	public ChromosomeFunction1(double errValue) {
		init(errValue);
	}

	public ChromosomeFunction1(InterpreterI interpreter, List<Boolean> chromosome2) {
		this.interpreter = interpreter;
	}

	private void init(double errValue) {
		rand = new Random();
		geneSize = new int[2];
		min = new double[2];
		max = new double[2];
		min[0] = -3.000;
		min[1] = 4.100;
		max[0] = 12.100;
		max[1] = 5.800;
		geneSize[0] = tamGen(errValue, min[0], max[0]);
		this.geneSize[1] = tamGen(errValue, min[1], max[1]);
		totalSize = geneSize[0] + geneSize[1];
		chromosome = new Boolean[totalSize];
		for(int i = 0; i < totalSize; i++) chromosome[i] = this.rand.nextBoolean();
	}
	
	private double getPhenotype(int genotypeNumber) {
		int ini = 0, value = 0, exp = geneSize[genotypeNumber]-1;
		if (genotypeNumber > 0) ini = geneSize[genotypeNumber-1]; //Busca el inicio del chromosoma donde empieza el gen
		for (int i = ini; i < geneSize[genotypeNumber]; i++) {
			value += Math.pow(Boolean.compare(chromosome[i], false), exp); //Eleva, de izquierda a derecha, el binario al exponente de su posición 
			exp--;
		}
		return value;
	}

	@Override
	public double getValor() {
		double x1 = getPhenotype(0), x2 = getPhenotype(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}

	@Override
	public int tamGen(double err, double min, double max) {
		return (int) (Math.log10(((max - min) / err) + 1) / Math.log10(2));
	}

	@Override
	public void mutate(double tasaMutacion) {
		for (int i=0; i< chromosome.length; i++) {
			if (rand.nextDouble() < tasaMutacion) 
				chromosome[i] = rand.nextBoolean();
		}		
	}

	@Override
	public Integer getSize() {
		return totalSize;
	}

	@Override
	public Boolean getElement(Integer i) {
		return chromosome[i];
	}

	@Override
	public InterpreterI getInterpreter() {
		return interpreter;
	}

	@Override
	public void invertElement(int i) {
		chromosome[i] = !chromosome[i];
		
	}

	@Override
	public Double getValue() {
		return getValor();
	}

}
