package model.chromosome;

import java.util.Comparator;

public class ChromosomeComparatorMin implements Comparator<ChromosomeI> {

	@Override
	public int compare(ChromosomeI o1, ChromosomeI o2) {
		if (o1.getValue() == null || o1.getValue() < o2.getValue())
		return -1;
		
		if (o2.getValue() == null || o1.getValue() > o2.getValue())
			return 1;
		
		return 0;
	}

}
