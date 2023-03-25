package model.debug;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.chromosome.TravellerChromosome;

public class TravellerChromosomeChecker {

	private TravellerChromosome tc;
	
	public TravellerChromosomeChecker(ChromosomeI tc) {
		if (tc instanceof TravellerChromosome)
			this.tc = (TravellerChromosome) tc;
	}
	
	public boolean isOk() {
		if (tc == null) {
			System.out.println("The chromsome is not a TravellerChromosome");
			return false;
		}
		List<GenI> genes = tc.getGenes();
		Set<String> positions = new TreeSet<>();
		Set<Double> cities = new TreeSet<>();
		if (genes == null) {
			System.out.println("Genes is null!");
			return false;
		}
		else if (genes.size() != 27) {
			System.out.println("The amount of cities don't correspond: " + genes.size() + " of 27");
			return false;
		}
		else {
			int i = 0;
			boolean isOk = true;
			for (GenI gen : genes) {
				if (Integer.parseInt(gen.getName().substring(1)) != i) {
					System.out.println("In the i position there is no x<i>");
					isOk = false;
					break;
				}
				if (positions.contains(gen.getName())) {
					System.out.println("Repeated name: " + gen.getName());
					isOk = false;
					break;
				}
				positions.add(gen.getName());
				if (cities.contains(gen.getValue())) {
					System.out.println("Repeated city: " + gen.getValue());
					isOk = false;
					break;
				}
				cities.add(gen.getValue());
				i++;
			}
			return isOk;
		}
	}
}
