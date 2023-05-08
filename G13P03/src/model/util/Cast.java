package model.util;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.practice3.TreeChromosome;

public class Cast {

	public static List<TreeChromosome> castChromosomeToTree(List<ChromosomeI> chrList) {
		List<TreeChromosome> treeList = new ArrayList<>();
		for (ChromosomeI chr : chrList)
			treeList.add((TreeChromosome) chr);
		return treeList;
	}
	
	public static List<ChromosomeI> castTreeToChromosome(List<TreeChromosome> treeList) {
		List<ChromosomeI> chrList = new ArrayList<>();
		for (ChromosomeI tree : treeList)
			chrList.add((ChromosomeI) tree);
		return chrList;
	}
}
