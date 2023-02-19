package model.selection;

public abstract class Tournament implements Selection {

	protected Integer contestantsAmount;
	
	public Tournament(Integer contestantsAmount) {
		this.contestantsAmount = contestantsAmount;
	}
}
