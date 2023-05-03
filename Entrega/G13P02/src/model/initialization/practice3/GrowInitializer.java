package model.initialization.practice3;

import model.MoldI;
import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;
import model.random.RandomGenerator;

public class GrowInitializer extends BinaryTreeInitializer {
	
	public GrowInitializer(MoldI mold, int maxDepth) {
		super(mold, maxDepth);
	}
	
	protected ArithmeticNode initNode() {
		if (depth < maxDepth) {
			int aleatoryInt = RandomGenerator.createAleatoryInt(
					ArithmeticEnum.values().length + TerminalEnum.values().length);
			if (aleatoryInt < ArithmeticEnum.values().length) {
				depth += 1;
				ArithmeticEnum arithmeticEnum = ArithmeticEnum.getRandom();
				ArithmeticNode leftBranch = initNode();
				ArithmeticNode rightBranch = initNode();
				depth -= 1;
				return new ArithmeticNode(arithmeticEnum, leftBranch, rightBranch);
			}
		}
		return new ArithmeticNode(TerminalEnum.getRandom());
	}
}
