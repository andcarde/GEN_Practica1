package model.tree;

public enum TerminalEnum {
	x, min2, min1, zero, one, two;

	public static double valueToInt(TerminalEnum fruit) {
		switch(fruit) {
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
}
