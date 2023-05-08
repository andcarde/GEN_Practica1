package model.chromosome.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.grammar.TreeBuilder;
import model.random.RandomGenerator;

public class CodonChromosome extends TreeChromosome {

	private static final int CODON_VALUES = 256;
	private List<Integer> codons;
	
	public CodonChromosome(MoldI mold) {
		super(mold);
		codons = new ArrayList<>();
		int codonsLength = TreeBuilder.obtainCodonsLength(mold.getMaxHeigth(), mold.getNumWraps());
		for (int i = 0; i < codonsLength; i++) {
			Integer codon = RandomGenerator.createAleatoryInt(CODON_VALUES);
			codons.add(codon);
		}
	}
	
	public CodonChromosome(CodonChromosome cc) {
		super(cc);
		codons = new ArrayList<>();
		for (Integer codon : cc.codons)
			codons.add(Integer.valueOf(codon));
	}
	
	public CodonChromosome copy() {
		return new CodonChromosome(this);
	}
	
	@Override
	public void evaluateValue() {
		raiz = TreeBuilder.buildTree(getCodons(), mold.getMaxHeigth());
		super.evaluateValue();
	}
	
	private List<Integer> getCodons() {
		List<Integer> codonsCopy = new ArrayList<>();
		for (Integer codon : codons)
			codonsCopy.add(Integer.valueOf(codon));
		return codonsCopy;
	}
	
	public void setCodon(int i, Integer codon) {
		codons.set(i, Integer.valueOf(codon));
	}

	public int getCodonsNumber() {
		return codons.size();
	}

	public void mutateCodon(int i) {
		Integer codon = RandomGenerator.createAleatoryInt(CODON_VALUES);
		codons.set(i, codon);
	}

	public Integer getCodon(int i) {
		return codons.get(i);
	}
}
