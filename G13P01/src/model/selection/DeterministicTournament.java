package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class DeterministicTournament extends Tournament {

	public DeterministicTournament(Integer contestantsAmount) {
		super(contestantsAmount);
	}
	
	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> selected = new ArrayList<>();
		if (this.contestantsAmount > 0) {
		List<ChromosomeI> populationCopy = List.copyOf(population);
		List<ChromosomeI> match;
		for (int i = 0; i < population.size(); i++) {
			match = new ArrayList<>();
			for (int j = 0; j < contestantsAmount; j++) {
				int randomIndex = RandomGenerator.createAleatoryInt(populationCopy.size());
				match.add(populationCopy.get(randomIndex));
				populationCopy.remove(randomIndex);
			}
			ChromosomeI winner = match.get(0);
			for (int j = 1; i < contestantsAmount; i++) {
				ChromosomeI actual = match.get(j);
				if (winner.getValue() < actual.getValue())
					winner = actual;
			}
			if (!selected.contains(winner))
				selected.add(winner);
			else
				selected.add(winner.copy());
			for (ChromosomeI ch : match)
				populationCopy.add(ch);
		}
		return selected;
	}
}
