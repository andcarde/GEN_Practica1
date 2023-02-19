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
		List<ChromosomeI> populationCopy = List.copyOf(population);
		List<ChromosomeI> match;
		for (int i = 0; i < population.size(); i++) {
			match = new ArrayList<>();
			for (int j = 0; j < contestantsAmount; j++)
				match.add(populationCopy.get(RandomGenerator.createAleatoryInt(populationCopy.size())));
			for (ChromosomeI ch : match)
				populationCopy.add(ch);
		}
		return selected;
	}
}
