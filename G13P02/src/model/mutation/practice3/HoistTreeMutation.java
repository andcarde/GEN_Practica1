package model.mutation.practice3;

import model.gen.practice3.ArithmeticNode;

public class HoistTreeMutation extends TreeMutation {

	public HoistTreeMutation(double probability) {
		super(probability);
	}

	@Override
	protected void mutateNode(ArithmeticNode node) {
		node = node.getRandomNoTerminalNode();
	}
}
