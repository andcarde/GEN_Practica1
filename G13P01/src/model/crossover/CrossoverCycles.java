package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.util.Converter;

public class CrossoverCycles extends Crossover {

	protected CrossoverCycles(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		HashMap<Integer, Object> s1 = new HashMap<>(), s2 = new HashMap<>();
		
		int next_index = 0;
		while (!s1.containsKey(next_index)) {
			s1.put(next_index, parent1.getGen(next_index).getGenoma());
			next_index = Converter.DoubleToInt((Double) parent2.getGen(next_index).getGenoma());
			if (next_index == 27) next_index--;
			
		}
		
		for (int i = 0; i < parent1.getGenes().size(); i++) {
			if (!s1.containsKey(parent1.getGen(i).getGenoma()))
				s1.put(i, parent1.getGen(i).getGenoma());
		}
		ChromosomeI son1 = parent1.copy();
		for (int i = 0; i < s1.size(); i++) {
			son1.getGen(i).assimilate(Converter.DoubleToInt((Double)s1.get(i)));
		}
		sons.add(son1);
		
		next_index = 0;
		while (!s2.containsKey(next_index)) {
			s2.put(next_index, parent2.getGen(next_index).getGenoma());
			next_index = Converter.DoubleToInt((Double) parent1.getGen(next_index).getGenoma());
			if (next_index == 27) next_index--;
		}
		
		for (int i = 0; i < parent2.getGenes().size(); i++) {
			if (!s2.containsKey(parent2.getGen(i).getGenoma()))
				s2.put(i, parent2.getGen(i).getGenoma());
		}
		ChromosomeI son2 = parent2.copy();
		for (int i = 0; i < s2.size(); i++) {
			son2.getGen(i).assimilate(Converter.DoubleToInt((Double)s2.get(i)));
		}
		sons.add(son2);
		return sons;
	}

}
