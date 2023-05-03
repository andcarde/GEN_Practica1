package model.fitness.practice3;

public class ObjetiveFunction {

	public static double getValue(double x) {
		return Math.pow(x, 4) + Math.pow(x, 3) + Math.pow(x, 2) + x + 1;
	}
}
