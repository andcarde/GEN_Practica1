package model.selection;

import graphic.TournamentRequest;

public class SelectionBuilder {

	public static Selection build(SelectionMethod selectionMethod, TournamentRequest tournamentRequest) {
		switch (selectionMethod) {
		case REMAINS:
			return new Remains();
		case ROULETTE:
			return new Roulette();
		case TOURNAMENT:
			return buildTournament(tournamentRequest);
		case TRUNCATION:
			return new Truncation();
		case UNIVERSAL_STOCHASTIC:
			return new UniversalStochastic();
		default:
			return null;
		}
	}
		
	private static Tournament buildTournament(TournamentRequest tournamentRequest) {
		TournamentMode tournamentMode = tournamentRequest.getTournamentMode();
		tournamentRequest.getChampionProbability();
		Integer contestantsAmount = tournamentRequest.getContestantsAmount();
		switch (tournamentMode) {
		case DETERMINISTIC:
			return new DeterministicTournament(contestantsAmount);
		case PROBABILIST:
			Double championProbability = tournamentRequest.getChampionProbability();
			return new ProbabilisticTournament(contestantsAmount, championProbability);
		default:
			return null;
		}
	}
}
