package graphic;

public class TournamentRequest {

	private Integer contestantsAmount;
	private Double championProbability;
	
	public TournamentRequest(Integer contestantsAmount) {
		this.contestantsAmount = contestantsAmount;
	}
	
	public void setChampionProbability(Double championProbability) {
		this.championProbability = championProbability;
	}

	public Integer getContestantsAmount() {
		return this.contestantsAmount;
	}

	public Double getChampionProbability() {
		return this.championProbability;
	}
}
