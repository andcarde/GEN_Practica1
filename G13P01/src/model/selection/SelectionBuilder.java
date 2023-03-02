package model.selection;

import graphic.TournamentRequest;

public class SelectionBuilder {

	public static SelectionI build(SelectionMethod selectionMethod, TournamentRequest tournamentRequest, Double double1) {
		switch (selectionMethod) {
		case REMAINS:
			return new Remains();
		case ROULETTE:
			return new Roulette();
		case RANKING:
			return new Ranking();
		case DETERMINISTIC_TOURNAMENT:
			return buildTournament(selectionMethod, tournamentRequest);
		case PROBABILISTIC_TOURNAMENT:
			return buildTournament(selectionMethod, tournamentRequest);
		case TRUNCATION:
			return new Truncation(double1);
		case UNIVERSAL_STOCHASTIC:
			return new UniversalStochastic();
		default:
			return null;
		}
	}
		
	private static Tournament buildTournament(SelectionMethod selectionMethod, TournamentRequest tournamentRequest) {
		Integer contestantsAmount = tournamentRequest.getContestantsAmount();
		switch (selectionMethod) {
		case DETERMINISTIC_TOURNAMENT:
			return new DeterministicTournament(contestantsAmount);
		case PROBABILISTIC_TOURNAMENT:
			Double championProbability = tournamentRequest.getChampionProbability();
			return new ProbabilisticTournament(contestantsAmount, championProbability);
		default:
			return null;
		}
	}
}
