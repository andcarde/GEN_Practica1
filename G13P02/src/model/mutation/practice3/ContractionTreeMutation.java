package model.mutation.practice3;

import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;

public class ContractionTreeMutation extends TreeMutation {
	
	public ContractionTreeMutation(double probability) {
		super(probability);
	}

	@Override
	protected void mutateNode(ArithmeticNode node) {
		ArithmeticNode selectedNode =  node.getRandomNoTerminalNode();
		selectedNode.toTerminalNode(TerminalEnum.getRandom());
	}
}
