package model.chromosome.practice3;

import model.MoldI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.fitness.CallbackInput;
import model.gen.practice3.ArithmeticNode;

public class TreeChromosome extends Chromosome {
	
	protected ArithmeticNode raiz;
	private Double functionValue;
	
	public TreeChromosome(MoldI mold) {
		super(mold);
	}
	
	public TreeChromosome(TreeChromosome chromosome) {
		super(chromosome);
		if (chromosome.raiz != null)
			this.raiz = chromosome.raiz.copy();
	}
	
	public TreeChromosome copy() {
		return new TreeChromosome(this);
	}
	
	public Integer getSize() {
		if (raiz == null)
			return 0;
		return raiz.getNumSons() + 1;
	}
	
	@Override
	public ChromosomeI createMutatedCopy() {
		return this.mold.getMutation().act(this.copy());
	}
	
	@Override
	public Double getValue() {
		// Necesario para corregir algún error inesperado
		if (phenotype == null || Double.isNaN(phenotype)) 
			evaluateFitness();
		return phenotype;
	}

	public String getGenesToString() {
		return raiz.pretty();
	}

	public ArithmeticNode getRaiz() {
		return raiz;
	}

	public void setRaiz(ArithmeticNode raiz) {
		this.raiz = raiz.copy();
	}
	
	@Override
	public void evaluateValue() {
		CallbackInput input = new CallbackInput();
		input.put("tree", raiz);
		functionValue = mold.getFunction().getValue(input);
	}
	
	@Override
	public void evaluateFitness() {
		phenotype = functionValue;
		if (mold.getBloating())
			phenotype += mold.getK() * (raiz.getNumSons() + 1);
	}
	
	@Override
	public String toString() {
		return raiz.toString();
	}
	
	@Override
	public String pretty() {
		return raiz.pretty();
	}

	@Override
	public double getFunctionValue() {
		if (functionValue == null || Double.isNaN(functionValue))
			evaluateValue();
		return functionValue;
	}
}
