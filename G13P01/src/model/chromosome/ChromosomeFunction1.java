package model.chromosome;

import java.util.Random;

public class ChromosomeFunction1 extends Chromosome<Boolean> {
	
	
	public ChromosomeFunction1(double errValue) {
		init(errValue);
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
	
	private double getPhenotype(double genotype) {
		return -1;
		//TODO
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

}
