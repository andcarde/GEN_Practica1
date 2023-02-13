package model.random;

import java.util.Random;

public class RandomGenerator {

	private static Random random;
	
	public static Boolean createAleatoryBoolean(Double probability) {
		if (RandomGenerator.random == null)
			RandomGenerator.random = new Random();
		return random.nextDouble() < probability;
	}
	
	public static Integer createAleatoryInt(Integer upperLimit) {
		if (RandomGenerator.random == null)
			RandomGenerator.random = new Random();
		return random.nextInt(upperLimit);
	}
	
	public static Double createAleatoryDouble() {
		if (RandomGenerator.random == null)
			RandomGenerator.random = new Random();
		return random.nextDouble();
	}
}
