package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.gen.practice1.GenI;
import model.random.RandomGenerator;
import model.util.Converter;

public class OrdinalCoding extends Crossover {

	protected OrdinalCoding(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		ChromosomeI son1 = coding(parent1.copy(), parent1);
		ChromosomeI son2 = coding(parent2.copy(), parent2);
		
		List<ChromosomeI> sons = onePointCrossover(son1, son2);
		
		son1 = decoding(sons.get(0)); 
		son2 = decoding(sons.get(1));
		
		sons.clear();
		sons.add(son1);
		sons.add(son2);
		
		return sons;
	}
	
	/***
	 * Dinamic list is initialized to contain all the valid city identifiers.
	 * @return
	 */
	private List<Integer> initializeList() {
		List<Integer> dynamic_list = new ArrayList<>();
		for (int i = 0; i < 28; i++) {
			if (i == 25) continue;
			dynamic_list.add(i);
		}
		return dynamic_list;
	}
	
	/***
	 * The cities identificators in the genes are transformed into relative positions.
	 * @param chromsome
	 * @param parent
	 * @return
	 */
	private ChromosomeI coding(ChromosomeI chromsome, ChromosomeI parent) {
		List<Integer> dynamic_list = initializeList();
		for (int i = 0; i < parent.getSize(); i++) {
			int value = Converter.DoubleToInt((Double) parent.getGen(i).getGenome());
			GenI aux = chromsome.getGen(i);
			aux.assimilate(dynamic_list.indexOf(value));
			chromsome.setGen(i, aux);
			dynamic_list.remove((Object) value);
		}
		return chromsome;
	}
	
	/***
	 * The relative positions in the genes are transformed into cities identificators.
	 * @param chromsome
	 * @return
	 */
	private ChromosomeI decoding(ChromosomeI chromsome) {
		List<Integer> dynamic_list = initializeList();
		for (int i = 0; i < chromsome.getSize(); i++) {
			int index = Converter.DoubleToInt((Double) chromsome.getGen(i).getGenome());
			GenI aux = chromsome.getGen(i);
			aux.assimilate(dynamic_list.get(index));
			chromsome.setGen(i, aux);
			dynamic_list.remove(index);
		}
		return chromsome;
	}
	
	/***
	 * The one point crossover takes place in the parents that have relative positions
	 * in his genes instead of cities identificator.
	 * @param parent1
	 * @param parent2
	 * @return
	 */
	private List<ChromosomeI> onePointCrossover(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		ChromosomeI son1 = parent1.copy();
		ChromosomeI son2 = parent2.copy();
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(mold.getGenes().size() - 1) + 1;
		
		Object genome1, genome2;
		for (int i = 0; i < cutPoint; i++) {
			genome1 = Converter.DoubleToInt((Double) parent1.getGen(i).getGenome());
			genome2 = Converter.DoubleToInt((Double) parent2.getGen(i).getGenome());
			son1.setGen(i, (GenI) son1.getGen(i).copy().assimilate(genome1));
			son2.setGen(i, (GenI) son1.getGen(i).copy().assimilate(genome2));
		}
		for (int i = cutPoint; i < mold.getGenes().size(); i++) {
			genome1 = Converter.DoubleToInt((Double) parent1.getGen(i).getGenome());
			genome2 = Converter.DoubleToInt((Double) parent2.getGen(i).getGenome());
			son1.setGen(i, (GenI) son1.getGen(i).copy().assimilate(genome2));
			son2.setGen(i, (GenI) son1.getGen(i).copy().assimilate(genome1));
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}

}
