package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeComparator;
import model.chromosome.ChromosomeComparatorMin;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;
import model.util.Pair;

public class Ranking implements SelectionI {
	
	private static final double _beta = 1.5;
	private boolean isMaxim;
	
	public Ranking(boolean isMaxim) {
		this.isMaxim = isMaxim;
	}

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		if (isMaxim) population.sort(new ChromosomeComparator());
		else population.sort(new ChromosomeComparatorMin());
		List<Pair<Double,Double>>  aux = puntuacion(population);
		return rouletteWithRanking(population, aux);
	}
	
	private List<Pair<Double,Double>>  puntuacion(List<ChromosomeI> population) {
		List<Pair<Double,Double>> ret = new ArrayList<>();
		double accPunc = 0.0;
		for (int i = 0; i < population.size(); ++i) {
			double probOfIth = (double)i/population.size();
			probOfIth *= 2*(_beta-1);
			probOfIth = _beta - probOfIth;
			probOfIth = (double)probOfIth * ((double)1/population.size());
			Pair<Double, Double> a = new Pair<Double, Double>(probOfIth, accPunc);
			ret.add(a);
			accPunc += probOfIth;
		}

		return ret;
	}
	
	private List<ChromosomeI> rouletteWithRanking(List<ChromosomeI> population, List<Pair<Double,Double>> punt) {
		List<ChromosomeI> ret = new ArrayList<>();
		double probability;
		for (int i = 0; i < population.size(); i++) {
			probability = RandomGenerator.createAleatoryDouble();
			ChromosomeI aux = population.get(getSelected(punt, probability));
			ret.add(aux);
		}
		return ret;
	}
	
	private int getSelected(List<Pair<Double, Double>> accumulated, double prob) {
		int closest_index = 0;
		double closest_value = 0;
		for (int i = 0; i < accumulated.size(); i++) {
			if (prob < accumulated.get(i).getR() && (accumulated.get(i).getR() - prob < abs(prob - closest_value))) {
				closest_index = i;
				closest_value = accumulated.get(i).getR();
			}
		}
		return closest_index;
	}

	private double abs(double val) {
		if (val < 0) return -val;
		return val;
	}

}
