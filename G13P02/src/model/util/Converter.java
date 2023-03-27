package model.util;

public class Converter {
	
	/***
	 * Converts a Double a to a int value v
	 * @param a
	 * @return v
	 */
	public static int DoubleToInt(Double a) {
		return Math.round(Math.round(a));
	}
}
