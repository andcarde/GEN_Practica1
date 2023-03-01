package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public abstract class Tournament implements SelectionI {

	protected Integer contestantsAmount;
	
	public Tournament(Integer contestantsAmount) {
		this.contestantsAmount = contestantsAmount;
	}
	
	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selected = new ArrayList<>();
		if (this.contestantsAmount > 0) {
			List<ChromosomeI> match;
			int size = population.size();
			for (int i = 0; i < size; i++) {
				match = new ArrayList<>();
				for (int j = 0; j < contestantsAmount; j++) {
					int randomIndex = RandomGenerator.createAleatoryInt(population.size());
					match.add(population.get(randomIndex));
					population.remove(randomIndex);
				}
				ChromosomeI winner = match.get(0);
				for (int j = 1; j < contestantsAmount; j++) {
					ChromosomeI actual = match.get(j);
					winner = battle(winner, actual);
				}
				if (!selected.contains(winner))
					selected.add(winner);
				else
					selected.add(winner.copy());
				for (ChromosomeI ch : match)
					population.add(ch);
			}
		}
		return selected;
	}

	protected abstract ChromosomeI battle(ChromosomeI contestant1, ChromosomeI contestant2);
}
