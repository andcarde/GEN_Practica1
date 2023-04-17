package model.mutation.practice2;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.fitness.practice2.FunctionTSP;
import model.gen.practice1.GenI;
import model.random.RandomGenerator;
import model.util.Converter;

public class CityEugenicMutation implements CityMutationI {
	
	private double mutationProbability;
	
	public CityEugenicMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		List<GenI> genes = chromosome.getGenes();
		if (!RandomGenerator.createAleatoryBoolean(mutationProbability))
			return genes;
		
		/* Sacamos el valor de cada ciudad, el cual se obtiene al hallar 
		 * la media de la distancia entre esta y sus adyacentes
		 */
		
		List<Double> valores = new ArrayList<>();
		int act, der, izq;
		Double value;
		
		// Primer valor
		act = Converter.DoubleToInt(genes.get(0).getValue());
		der = Converter.DoubleToInt(genes.get(1).getValue());
		value = (FunctionTSP.getDist(act, 25) + FunctionTSP.getDist(act, der)) / 2 + 0.0;
		valores.add(value);
		
		// Valores intermedios
		for (int i = 1; i < genes.size() - 1; i++) {
			act = Converter.DoubleToInt(genes.get(i).getValue());
			izq = Converter.DoubleToInt(genes.get(i - 1).getValue());
			der = Converter.DoubleToInt(genes.get(i + 1).getValue());
			value = (FunctionTSP.getDist(act, izq) + FunctionTSP.getDist(act, der)) / 2 + 0.0; 
			valores.add(value);
		}
		
		// Últtimo valor
		act = Converter.DoubleToInt(genes.get(genes.size() - 1).getValue());
		izq = Converter.DoubleToInt(genes.get(genes.size() - 2).getValue());
		value = (FunctionTSP.getDist(act, izq) + FunctionTSP.getDist(act, 25)) / 2 + 0.0; 
		valores.add(value);
		
		// Hallamos los indices de los dos peores valores
		int worst_index = 0;
		int less_worse_index = 1;
		if (valores.get(less_worse_index) > valores.get(worst_index)) {
			worst_index = 1;
			less_worse_index = 0;
		}
		for (int i = 2; i < valores.size(); i++) {
			if (valores.get(i) > valores.get(less_worse_index)) {
				if (valores.get(i) > valores.get(worst_index)) {
					less_worse_index = worst_index;
					worst_index = i;
				}
				else less_worse_index = i;
			}
		}
		
		// Intercambiamos de posición a los genes correspondientes.
		GenI worst = genes.get(worst_index);
		GenI less_worse = genes.get(less_worse_index);
		Double worstValue = worst.getValue();
		worst.assimilate(less_worse.getValue());
		less_worse.assimilate(worstValue);
		
		return genes;
	}
}
