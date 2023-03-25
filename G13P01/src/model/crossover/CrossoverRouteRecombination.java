package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.ConnectionTable;
import model.MoldI;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class CrossoverRouteRecombination extends Crossover {
	
	public CrossoverRouteRecombination(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		ConnectionTable connections = new ConnectionTable(parent1, parent2);
		List<ChromosomeI> sons = new ArrayList<>();
		int cur_index = 0;
		
		HashMap<Integer, Object> s1 = new HashMap<>();
		for (int i = 0; i < parent1.getSize(); i++) {
			s1.put(i, parent1.getGen(cur_index).getGenome());
			cur_index = connections.getLeastConnected(cur_index, s1);
		}
		ChromosomeI son1 = parent1.copy();
		for (int i = 0; i < s1.size(); i++) {
			son1.getGen(i).assimilate(s1.get(i));
		}
		
		cur_index = 0;
		HashMap<Integer, Object> s2 = new HashMap<>();
		for (int i = 0; i < parent2.getSize(); i++) {
			s2.put(i, parent1.getGen(cur_index).getGenome());
			cur_index = connections.getLeastConnected(cur_index, s2);
		}
		ChromosomeI son2 = parent1.copy();
		for (int i = 0; i < s1.size(); i++) {
			son2.getGen(i).assimilate(s1.get(i));
		}
		
		
		if (RandomGenerator.createAleatoryBoolean(0.5)) {
			sons.add(son1);			
			sons.add(son2);
		}
		else {
			sons.add(son2);
			sons.add(son1);			
		}
		return sons;
	}

}
