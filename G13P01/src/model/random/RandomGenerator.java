package model.random;

import java.util.Random;

public class RandomGenerator {

	private static final Long seed = 534538184710L;
	private static Random random;
	
	private static Random getRandom() {
		if (RandomGenerator.random == null)
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
