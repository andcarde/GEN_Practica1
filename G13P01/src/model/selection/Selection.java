package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public interface Selection {

	List<ChromosomeI> act(List<ChromosomeI> population);
}
