package model.mutation.practice3;

import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;

public class FunctionalTreeMutation extends TreeMutation {
	
	public FunctionalTreeMutation(double probability) {
		super(probability);
	}
	
	@Override
	protected void mutateNode(ArithmeticNode node) {
		ArithmeticNode selectedNode =  node.getRandomNoTerminalNode();
		selectedNode.setKnot(ArithmeticEnum.getRandom());
	}
}
