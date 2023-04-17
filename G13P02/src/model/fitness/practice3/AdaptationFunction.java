package model.fitness.practice3;

import java.util.ArrayList;
import java.util.List;

import model.fitness.CallbackInput;
import model.fitness.Function;
import model.fitness.Input;
import model.fitness.Variable;
import model.gen.practice1.GenType;

public class AdaptationFunction extends Function {

	// Precondition: DATASET_SIZE > 1
	private static int DATASET_SIZE = 101;
	private static double X_BELOW_LIMIT = -1;
	private static double X_UPPER_LIMIT = 1;
	private static double X_STEP = (X_UPPER_LIMIT - X_BELOW_LIMIT) / (DATASET_SIZE - 1);
	
	public AdaptationFunction() {
		super.isMaxim = true;
	}
	
	@Override
	public Double getValue(Input<?> inputG) {
		Double d = 0.0;
		CallbackInput input = (CallbackInput) inputG;
		Callback callback = input.get("tree");
		IntervalIterator ii = new IntervalIterator(X_BELOW_LIMIT, X_UPPER_LIMIT, X_STEP);
		for (int i = 0; i < DATASET_SIZE; i++) {
			d += Math.pow((callback.getValue(ii.next()) - ObjetiveFunction.getValue(ii.next())), 2);
			Math.pow(i, 2);
		}
		d /= DATASET_SIZE;
		return Math.sqrt(d);
	}

	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		String variableName = "tree";
		variables.add(new CallbackVariable(variableName));
		return variables;
	}

	@Override
	public GenType getGenType() {
		return GenType.TREE;
	}
}
