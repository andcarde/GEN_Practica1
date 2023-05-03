package model.chromosome.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.gen.practice3.BinaryGen;
import model.grammar.TreeBuilder;
import model.random.RandomGenerator;

public class CodonChromosome extends TreeChromosome {

	private static final int CODON_VALUES = 256;
	private List<BinaryGen> genes;
	
	public CodonChromosome(MoldI mold) {
		super(mold);
		genes = new ArrayList<>();
		int codonsLength = TreeBuilder.obtainCodonsLength(mold.getMaxHeigth(), mold.getNumWraps());
		for (int i = 0; i < codonsLength; i++) {
			int codon = RandomGenerator.createAleatoryInt(CODON_VALUES);
			genes.add(new BinaryGen(codon));
		}
	}
	
	public CodonChromosome(CodonChromosome cc) {
		super(cc);
		genes = new ArrayList<>();
		for (BinaryGen gen : cc.genes)
			genes.add(gen.copy());
	}
	
	@Override
	public void evaluate() {
		List<Integer> codons = new ArrayList<>();
		for (BinaryGen gen : genes)
			codons.add(gen.toInteger());
		this.raiz = TreeBuilder.buildTree(codons, this.mold.getMaxHeigth());
		super.evaluate();
	}
	
	public List<List<Boolean>> getBits() {
		List<List<Boolean>> bits = new ArrayList<>();
		for (BinaryGen gen : genes)
			bits.add(gen.getBits());
		return bits;
	}
	
	public CodonChromosome copy() {
		return new CodonChromosome(this);
	}
	
	@Override
	public List<BinaryGen> getGenes() {
		return genes;
	}
	
	public void setGen(int i, Object gen) {
		genes.set(i, (BinaryGen) gen);
		
	}

	public void assimilate(List<List<Boolean>> genomes) {
		for (int i = 0; i < genomes.size(); i++) {
			BinaryGen gen = genes.get(i).copy();
			gen.setBits(genomes.get(i));
			genes.set(i,gen);
		}
		
	}
	
	public Integer getSize() {
		Integer num = 0;
		for (BinaryGen gen : genes) {
			num+=gen.getSize();
		}
		return num;
	}
}

