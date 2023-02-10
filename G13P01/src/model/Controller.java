package model;

public class Controller {

	private final Integer GENERATION_AMOUNT;
	private final Integer POBLATION_AMOUNT;
	private final SelectionType SELECTION_TYPE;
	
	public run() {
		initilize();
		evaluate();
		for (int i = 0; i < GENERATION_AMOUNT; i++) {
			selection();
			cruce();
			mutation();
			evaluate();
		}
	}
}
