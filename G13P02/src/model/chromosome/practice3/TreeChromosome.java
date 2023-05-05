package model.chromosome.practice3;

import java.util.List;

import model.MoldI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.fitness.CallbackInput;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.BinaryGen;

public class TreeChromosome extends Chromosome {
	
	protected ArithmeticNode raiz;
	private Double functionValue;
	
	public TreeChromosome(MoldI mold) {
		super(mold);
	}
	
	public TreeChromosome(TreeChromosome chromosome) {
		super(chromosome);
		if (chromosome.raiz != null) this.raiz = chromosome.raiz.copy();
	}
	
	public TreeChromosome copy() {
		return new TreeChromosome(this);
	}
	
	public Integer getSize() {
		return raiz.getNumSons()+1;
	}
	
	public double getBasicValue() {
		if (functionValue == null || Double.isNaN(functionValue))
			evaluate();
		return functionValue;
	}
	
	public ChromosomeI createMutatedCopy() {
		return this.mold.getMutation().act(this);
	}
	
	@Override
	public Double getValue() {
		// Necesario para corregir algún error inesperado
		if (phenotype == null || Double.isNaN(phenotype)) 
			evaluate();
		return phenotype;
	}

	public String getGenesToString() {
		return raiz.pretty();
	}

	public ArithmeticNode getRaiz() {
		return raiz;
	}

	public void setRaiz(ArithmeticNode raiz) {
		this.raiz = raiz;
	}
	
	public Double getValue(Double x, ArithmeticNode node) {
		return node.getValue(x);
	}
	
	@Override
	public void evaluate() {
		CallbackInput input = new CallbackInput();
		input.put("tree", raiz);
		this.phenotype = this.mold.getFunction().getValue(input);
		this.functionValue = phenotype;
		if (mold.getBloating())
			this.phenotype += mold.getK() * raiz.getNumSons();
	}
	
	@Override
	public String toString() {
		return this.raiz.toString();
	}
	
	@Override
	public String pretty() {
		return this.raiz.pretty();
	}

	@Override
	public void mutate() {
		this.mold.getMutation().act(this);
	}

	@Override
	public List<BinaryGen> getGenes() {
		return null;
	}

	@Override
	public double getFunctionValue() {
		if (functionValue == null || Double.isNaN(functionValue))
			evaluate();
		return functionValue;
	}
}
