package not_used;

import java.util.ArrayList;
import java.util.List;

import model.PopulationTable;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;
import model.selection.SelectionI;

public class Tournament implements SelectionI {

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selection = new ArrayList<>();
		PopulationTable table = new PopulationTable(population);		
		
		for (int i = 0; i < population.size(); i++) {
			List<ChromosomeI> candidates = new ArrayList<>();
			candidates.add(population.get(RandomGenerator.createAleatoryInt(population.size())));
			candidates.add(population.get(RandomGenerator.createAleatoryInt(population.size())));
			candidates.add(population.get(RandomGenerator.createAleatoryInt(population.size())));
			
			selection.add(getWinner(candidates, RandomGenerator.createAleatoryBoolean(0.5)));
		}
		return selection;
	}
	
	private ChromosomeI getWinner(List<ChromosomeI> candidates, boolean chooseBiggestFitness) {
		ChromosomeI winner = candidates.get(0);
		for (int i = 1; i < candidates.size(); i++) {
			//Si el actual tiene mejor fitness que el mejor, ya sea por tener menor o mayor 
			//fitness dependiendo del booleano, se actualiza el mejor
			if ((chooseBiggestFitness && candidates.get(i).getValue() > winner.getValue()) 
					|| (!chooseBiggestFitness && candidates.get(i).getValue() < winner.getValue()))
				winner = candidates.get(i);
			//Si son iguales, se elige aleatoriamente
			else if (candidates.get(i).getValue() == winner.getValue() && RandomGenerator.createAleatoryBoolean(0.5)) {
				winner = candidates.get(i);
			}
		}
		return winner;
	}
}
