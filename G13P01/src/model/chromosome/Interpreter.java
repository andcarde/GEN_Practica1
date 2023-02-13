package model.chromosome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter implements InterpreterI {

	private List<String> variableNames;
	private List<String> variableTypes;
	private List<Integer> genLengths;
	
	Interpreter(List<String> variableNames, List<Integer> genLengths) {
		this.variableNames = variableNames;
		this.genLengths = genLengths;
	}
	
	Map<String, Object> translate(ChromosomeI chromosome) {
		Map<String, Object> map = new HashMap<>();
		Integer genNumber = 0;
		Integer genLimit = this.genLengths.get(genNumber);
		List<Boolean> gen = new ArrayList<>();
		for (int i = 0; i < chromosome.getSize(); i++) {
			if (i == genLimit) {
				Object genValue = parse(gen, this.variableTypes.get(genNumber));
				map.put(this.variableNames.get(genNumber), genValue);
				gen = new ArrayList<>();
				genNumber++;
				genLimit += this.genLengths.get(genNumber);
			}
			gen.add(chromosome.getElement(i));
		}
		return map;
	}
	
	private static Object parse(List<Boolean> gen, String type) {
		String[] parts = type.split("#");
		if (parts[0] == "BoundedDouble")
			return toBoundedDouble(gen, parts[1]);
		return null;
	}
	
	private static Double toBoundedDouble(List<Boolean> gen, String bounds) {
		String[] arrayBounds = bounds.split("#");
		Double belowLimit = Double.valueOf(arrayBounds[0]);
		Double upperLimit = Double.valueOf(arrayBounds[1]);
		Double width = upperLimit - belowLimit;
		Double bitValue = width / Math.pow(gen.size(), 2);
		Double value = 0.0;
		Integer pow = 1;
		for (int i = 0; i < gen.size(); i++) {
			if (gen.get(i))
				value +=  bitValue * pow;
			pow *= 2;
		}
		value += belowLimit;
		return value;
	}
}
