package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public class ProbabilisticTournament extends Tournament {

	private Double championProbability;
	
	public ProbabilisticTournament(Integer contestantsAmount, Double championProbability) {
		super(contestantsAmount);
		this.championProbability = championProbability;
	}

	@Override
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		// TODO Falta por implementar
		return null;
	}
}
