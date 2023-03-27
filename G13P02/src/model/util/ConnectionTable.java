package model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class ConnectionTable {
	
	private HashMap<Integer, List<Integer>> connections;
	
	public ConnectionTable(ChromosomeI c1, ChromosomeI c2) {
		connections = new HashMap<>();
		for (int i = 0; i <= c1.getSize(); i++) {
			if (i == 25) continue;
			connections.put(i, getAdyacents(i, c1, c2));
		}
	}
	
	private List<Integer> getAdyacents(int city, ChromosomeI c1, ChromosomeI c2) {
		List<Integer> adjacents = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		int index = c1.indexOf(city);
		int der = 0, izq = 0;
		if (index == 0) 
			izq = c1.getSize()-1;
		else 
			izq = index-1;
		
		if (index != c1.getSize()-1) der = 0;
		
		
		adjacents.add(Converter.DoubleToInt((Double)c1.getGen(izq).getGenome()));
		set.add(Converter.DoubleToInt((Double)c1.getGen(izq).getGenome()));
		if (!set.contains(Converter.DoubleToInt((Double)c1.getGen(der).getGenome()))) {
			adjacents.add(Converter.DoubleToInt((Double)c1.getGen(der).getGenome()));
			set.add(Converter.DoubleToInt((Double)c1.getGen(der).getGenome()));
		}
		
		index = c2.indexOf(city);
		if (index == 0) 
			izq = c1.getSize()-1;
		else 
			izq = index-1;
		
		if (index != c1.getSize()-1) der = 0;
		
		if (!set.contains(Converter.DoubleToInt((Double)c2.getGen(der).getGenome()))) {
			adjacents.add(Converter.DoubleToInt((Double)c2.getGen(der).getGenome()));
			set.add(Converter.DoubleToInt((Double)c2.getGen(der).getGenome()));
		}
		
		if (!set.contains(Converter.DoubleToInt((Double)c2.getGen(izq).getGenome()))) {
			adjacents.add(Converter.DoubleToInt((Double)c2.getGen(izq).getGenome()));
			set.add(Converter.DoubleToInt((Double)c2.getGen(izq).getGenome()));
		}
		
		return adjacents;
	}
	
	/**
     * Para cada gen, se devuelve una lista con los genes adyacentes
     * a ambos cromosomas
     */
	public HashMap<Integer, List<Integer>> getConnections() {
		return connections;
	}

	
	/**
     * Dada una ciudad, se devuelve la ciudad dentro de su lista con menos conexiones
     * @param city La ciudad desde donde se busca
     */
	public int getLeastConnectedCity(int city, HashMap<Integer, Object> existence) {
		int leastConnectedCity = -1;
		for (Integer gen : connections.get(city)) {
			if (existence.containsValue(gen)) continue;
			
			if (leastConnectedCity == -1 || connections.get(gen).size() < connections.get(leastConnectedCity).size() || (connections.get(gen).size() == connections.get(leastConnectedCity).size() && RandomGenerator.createAleatoryBoolean(0.5))) 
				leastConnectedCity = gen;
			
		} 
		return leastConnectedCity;
	}

}
