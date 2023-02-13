package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public interface SelectionMethod {

	List<ChromosomeI> select(List<ChromosomeI> population);
}
