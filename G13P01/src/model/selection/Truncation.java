package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;

public class Truncation implements SelectionI {
	
	private double trunc;
	
	public Truncation(Double t) {
		trunc = t;
	}

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		int proportion = (int) (1/trunc);
		List<ChromosomeI> ret = new ArrayList<>();
		for (int i = 0; i < population.size() && i < trunc*population.size(); i++) {
			int number_of_copies = 0;
			while (number_of_copies< proportion) {
				ret.add(population.get(i).copy());
				number_of_copies++;
			}
		}
		return ret;
	}

}
