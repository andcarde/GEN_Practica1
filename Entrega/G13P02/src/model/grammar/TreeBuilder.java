package model.grammar;

import java.util.List;

import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;

public class TreeBuilder {
	
	/* Sobre el cromosoma de la gramática evolutiva:
	 * - Wraps: Su número debe de poder seleccionarse entre 2 y 3.
	 * - Cruce: Deben de estar disponibles los métodos Uniforme y Monopunto.
	 */
	
	/* Definición de la gramática
	 * S (Símbolo inicial): <exp>
	 * <op> ::= |*| or |+| or |-|
	 * <sym> ::= |-2| or |-1| or |0| or |1| or |2| or |x|
	 * <exp> ::= <op>(<exp>, <exp>) or <sym>
	 */
	
	public static int obtainCodonsLength(int maxHeight, int numWraps) {
		// (Math.pow(2, maxHeight - 1) - 1) * 2 + Math.pow(2, maxHeight - 1)
		int length = (int) (Math.pow(2, maxHeight - 1)) * 3 - 2;
		if (length % numWraps == 0)
			return length / numWraps;
		return length / numWraps + 1;
	}
	
	public static ArithmeticNode buildTree(List<Integer> codons, int maxHeight) {
		return buildNode(new CodonsIterator(codons), maxHeight);
	}
	
	private static ArithmeticNode buildNode(CodonsIterator it, int maxHeight) {
		if (maxHeight == 1)
			return new ArithmeticNode(pickSymbol(it));
		if (it.nextModule(2) == 0)
			return new ArithmeticNode(pickOperation(it), buildNode(it, maxHeight - 1), buildNode(it, maxHeight - 1));
		else
			return new ArithmeticNode(pickSymbol(it));
	}
	
	private static ArithmeticEnum pickOperation(CodonsIterator it) {
		int index = it.nextModule(ArithmeticEnum.values().length);
		return ArithmeticEnum.values()[index];
	}
	
	private static TerminalEnum pickSymbol(CodonsIterator it) {
		int index = it.nextModule(TerminalEnum.values().length);
		return TerminalEnum.values()[index];
	}
}
