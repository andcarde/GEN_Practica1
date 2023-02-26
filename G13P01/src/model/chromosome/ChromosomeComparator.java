package model.chromosome;

import java.util.Comparator;

public class ChromosomeComparator implements Comparator<ChromosomeI> {

	@Override
	public int compare(ChromosomeI o1, ChromosomeI o2) {
		if (o1.getValue() == null || o2.getValue() == null) {
			o1.evaluate();
			o2.evaluate();
			System.out.println("GetValue es nulo");
		}
		if (o1.getValue() < o2.getValue())
		return 1;
		
		if (o1.getValue() == o2.getValue())
			return 0;
		
		return -1;
	}

}
