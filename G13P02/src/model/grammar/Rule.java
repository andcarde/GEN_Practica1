package model.grammar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;

public class Rule {
	
	String first = "exp";
	String waitingFirs = "<op>";
	/*
	 * Símbolo inicial: <exp>
	 * <op> ::= |*| or |+| or |-|
	 * <sym> ::= |-2| or |-1| or |0| or |1| or |2| or |x| or |no|
	 * <exp> ::= <op>(<exp>, <exp>) or <sym>
	*/
	
	public static List<Integer> treeToCodons(ArithmeticNode raiz) {
		List<Integer> codons = new ArrayList<>();
		Map<Integer, Object> toElement = new HashMap<>();
		Map<Object, Integer> toInteger = new HashMap<>();
		int count = 0;
		for (ArithmeticEnum ae : ArithmeticEnum.values()) {
			toElement.put(count, ae);
			toInteger.put(ae, count);
			count++;
		}
		for (TerminalEnum te : TerminalEnum.values()) {
			toElement.put(count, te);
			toInteger.put(te, count);
			count++;
		}
		Deque<ArithmeticNode> stack = new ArrayDeque<>();
		stack.push(raiz);
		ArithmeticNode actual;
		while (!stack.isEmpty()) {
			actual = stack.pop();
			if (actual.isLeaf())
				codons.add(toInteger.get(actual.getFruit()));
			else {
				codons.add(toInteger.get(actual.getKnot()));
				ArithmeticNode rightBranch = actual.getRightBranch();
				if (rightBranch != null)
					stack.push(rightBranch);
				ArithmeticNode leftBranch = actual.getLeftBranch();
				if (leftBranch != null)
					stack.push(leftBranch);
			}
		}
		return codons;
	}
	
	public static List<Integer> wrapCodons(int numWraps, List<Integer> originalCodons) {
		List<Integer> codons = new ArrayList<>();
		final int codonsLength = originalCodons.size() / numWraps;
		for (int i = 0; i < codonsLength; i++) {
			
		}
		if (originalCodons.size() % numWraps != 0)
		for ()
		return codons;
	}
}
