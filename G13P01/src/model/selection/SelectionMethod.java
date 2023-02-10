package model.selection;

import java.util.List;

public interface SelectionMethod {

	int n;
	
	List<Chromosome> select(Map<Chromosome, Double>) {
		
	}
}
