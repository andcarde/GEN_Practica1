package model.mutation.practice3;

import model.gen.practice3.ArithmeticNode;

public class PermutationTreeMutation extends TreeMutation {
	
	public PermutationTreeMutation(double probability) {
		super(probability);
	}
	
	@Override
	protected void mutateNode(ArithmeticNode node) {
		ArithmeticNode selectedNode =  node.getRandomNoTerminalNode();
		ArithmeticNode leftBranch = selectedNode.getLeftBranch();
		selectedNode.setLeftBranch(selectedNode.getRightBranch());
		selectedNode.setRightBranch(leftBranch);
	}
}
