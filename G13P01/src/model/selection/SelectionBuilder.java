package model.selection;

import control.TournamentRequest;

public class SelectionBuilder {

	public static SelectionI build(SelectionMethod selectionMethod, TournamentRequest tournamentRequest, boolean isMaximization,Double trunc) {
		switch (selectionMethod) {
		case REMAINS:
			return new Remains(isMaximization);
		case ROULETTE:
			return new Roulette(isMaximization);
		case RANKING:
			return new Ranking(isMaximization);
		case DETERMINISTIC_TOURNAMENT:
			return buildTournament(selectionMethod, tournamentRequest, isMaximization);
		case PROBABILISTIC_TOURNAMENT:
			return buildTournament(selectionMethod, tournamentRequest, isMaximization);
		case TRUNCATION:
			return new Truncation(trunc, isMaximization);
		case UNIVERSAL_STOCHASTIC:
			return new UniversalStochastic(isMaximization);
		default:
			return null;
		}
	}
		
	private static Tournament buildTournament(SelectionMethod selectionMethod, TournamentRequest tournamentRequest, boolean isMaximization) {
		Integer contestantsAmount = tournamentRequest.getContestantsAmount();
		switch (selectionMethod) {
		case DETERMINISTIC_TOURNAMENT:
			return new DeterministicTournament(isMaximization, contestantsAmount);
		case PROBABILISTIC_TOURNAMENT:
			Double championProbability = tournamentRequest.getChampionProbability();
			return new ProbabilisticTournament(isMaximization, contestantsAmount, championProbability);
		default:
			return null;
		}
	}
}
