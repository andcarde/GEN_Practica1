package model.gen.practice3;

import model.random.RandomGenerator;

public enum ArithmeticEnum {

	ADD("+"), SUB("-"), MUL("*");

	public static double calculate(ArithmeticEnum arithmeticEnum, double value1, double value2) {
		switch(arithmeticEnum) {
			case ADD:
				return value1 + value2;
			case SUB:
				return value1 - value2;
			case MUL:
				return value1 * value2;
			default:
				return 0;
		}
	}
	
	private String operator;
	
	private ArithmeticEnum(String operator) {
		this.operator = operator;
	}
	
	public String toString() {
		return operator;
	}
	
	public static ArithmeticEnum getRandom() {
		ArithmeticEnum[] values = ArithmeticEnum.values();
		int index = RandomGenerator.createAleatoryInt(values.length);
		return values[index];
	}
}
