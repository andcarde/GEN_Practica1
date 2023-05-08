package model.initialization.practice3;

import model.MoldI;
import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;
import model.random.RandomGenerator;

public class GrowInitializer extends BinaryTreeInitializer {
	
	private boolean first;
	
	public GrowInitializer(MoldI mold, int maxDepth) {
		super(mold, maxDepth);
		first = true;
	}
	
	protected ArithmeticNode initNode() {
		if (depth < maxDepth) {
			if (first || RandomGenerator.createAleatoryBoolean()) {
				first = false;
				depth++;
				ArithmeticEnum arithmeticEnum = ArithmeticEnum.getRandom();
				ArithmeticNode leftBranch = initNode();
				ArithmeticNode rightBranch = initNode();
				depth--;
				return new ArithmeticNode(arithmeticEnum, leftBranch, rightBranch);
			}
		}
		return new ArithmeticNode(TerminalEnum.getRandom());
	}
}
