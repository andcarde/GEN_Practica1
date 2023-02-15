package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public interface SelectionMethod {

	List<ChromosomeI> act(List<ChromosomeI> population);
}
