package graphic;

import model.selection.TournamentMode;

public class TournamentRequest {

	private Integer contestantsAmount;
	private TournamentMode tournamentMode;
	private Double championProbability;
	
	public TournamentRequest(TournamentMode tournamentMode, Integer contestantsAmount) {
		this.tournamentMode = tournamentMode;
		this.contestantsAmount = contestantsAmount;
	}
	
	public void setChampionProbability(Integer championPercentage) {
		this.championProbability = (double) championPercentage / 100;
	}

	public Integer getContestantsAmount() {
		return this.contestantsAmount;
	}

	public TournamentMode getTournamentMode() {
		return this.tournamentMode;
	}

	public Double getChampionProbability() {
		return this.championProbability;
	}
}
