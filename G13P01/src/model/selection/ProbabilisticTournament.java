package model.selection;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class ProbabilisticTournament extends Tournament {

	private Double championProbability;
	
	public ProbabilisticTournament(Integer contestantsAmount, Double championProbability) {
		super(contestantsAmount);
		this.championProbability = championProbability;
	}
	
	@Override
	protected ChromosomeI battle(ChromosomeI contestant1, ChromosomeI contestant2) {
		ChromosomeI winner = null;
		if (contestant1.getValue() == contestant2.getValue()) {
			if (RandomGenerator.createAleatoryDouble() < 0.5)
				winner = contestant1;
			else
				winner =  contestant2;
		}
		else if (this.championProbability > RandomGenerator.createAleatoryDouble()) {
			if (Selection.compare(contestant1, contestant2) == -1)
				winner = contestant1;
			else
				winner = contestant2;
		}
		else {
			if (Selection.compare(contestant1, contestant2) == 1)
				winner = contestant1;
			else
				winner = contestant2;
		}
		return winner;
	}
}
