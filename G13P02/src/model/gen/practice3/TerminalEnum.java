package model.gen.practice3;

import model.random.RandomGenerator;

public enum TerminalEnum {
	
	x, min2, min1, zero, one, two;

	public static int valueToInt(TerminalEnum terminalEnum) {
		switch(terminalEnum) {
			case min2:
				return -2;
			case min1:
				return -1;
			case zero:
				return 0;
			case one:
				return 1;
			case two:
				return 2;
			default:
				return -3;
		}
	}
	
	public static String toString(TerminalEnum terminalEnum) {
		switch(terminalEnum) {
			case x:
				return "x";
			default:
				return Integer.toString(valueToInt(terminalEnum));
		}
	}
	
	public static TerminalEnum getRandom() {
		TerminalEnum[] values = TerminalEnum.values();
		int index = RandomGenerator.createAleatoryInt(values.length);
		return values[index];
	}
}
