package model.tree;

public enum ArithmeticEnum {

	ADD, SUB, MUL;

	public static double calculate(double value, ArithmeticEnum knot, double value2) {
		switch(knot) {
			case ADD:
				return value + value2;
			case SUB:
				return value - value2;
			case MUL:
				return value * value2;
		}
		return 0;
	}
}
