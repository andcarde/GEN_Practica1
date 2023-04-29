package model.random;

import java.util.Random;

public class RandomGenerator {

	private static Long seed;
	private static Random random;
	
	
	public static void setSeed(Long seed) {
		RandomGenerator.seed = seed;
	}
	
	private static Random getRandom() {
		if (RandomGenerator.random == null)
			if (seed == null)
				RandomGenerator.random = new Random();
			else
				RandomGenerator.random = new Random(RandomGenerator.seed);
		return RandomGenerator.random;
	}
	
	public static Boolean createAleatoryBoolean(Double probability) {
		return getRandom().nextDouble() < probability;
	}
	
	public static Boolean createAleatoryBoolean() {
		return getRandom().nextBoolean();
	}
	
	public static Integer createAleatoryInt(Integer upperLimit) {
		return getRandom().nextInt(upperLimit);
	}
	
	public static Double createAleatoryDouble() {
		return getRandom().nextDouble();
	}
	
	public static Double createAleatoryDoublePlus(Double belowLimit, Double upperLimit) {
		return createAleatoryDouble() * upperLimit + belowLimit;
	}
}
