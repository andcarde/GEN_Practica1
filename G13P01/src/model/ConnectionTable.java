package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;
import model.util.Converter;

public class ConnectionTable {
	
	private HashMap<Integer, List<Integer>> connections;
	public ConnectionTable(ChromosomeI c1, ChromosomeI c2) {
		connections = new HashMap<>();
		for (int i = 0; i < c1.getSize(); i++) {
			connections.put(i, getAdyacents(i, c1, c2));
		}
	}
	
	private List<Integer> getAdyacents(int index, ChromosomeI c1, ChromosomeI c2) {
		List<Integer> adjacents = new ArrayList<>();
		HashSet<Object> set = new HashSet<>();
		int der = 0, izq = 0;
		if (index == 0) izq = Converter.DoubleToInt((Double)c1.getGen(c1.getSize()-1).getGenoma());
		else Converter.DoubleToInt((Double)c1.getGen(index-1).getGenoma());
		
		if (index == c1.getSize()-1) der = Converter.DoubleToInt((Double)c1.getGen(0).getGenoma());
		else Converter.DoubleToInt((Double)c1.getGen(index+1).getGenoma());
		
		adjacents.add(Converter.DoubleToInt((Double)c1.getGen(izq).getGenoma()));
		set.add(c1.getGen(izq).getGenoma());
		if (!set.contains(c1.getGen(der).getGenoma())) {
			adjacents.add(Converter.DoubleToInt((Double)c1.getGen(der).getGenoma()));
			set.add(c1.getGen(der).getGenoma());
		}
		
		if (!set.contains(c2.getGen(der).getGenoma())) {
			adjacents.add(Converter.DoubleToInt((Double)c2.getGen(der).getGenoma()));
			set.add(c2.getGen(der).getGenoma());
		}
		
		if (!set.contains(c2.getGen(izq).getGenoma())) {
			adjacents.add(Converter.DoubleToInt((Double)c2.getGen(izq).getGenoma()));
			set.add(c2.getGen(izq).getGenoma());
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
     * Dado una clave, se devuelve el indice del valor dentro de su lista con menos conexiones
     * @param index La clave desde donde se busca
     */
	public int getLeastConnected(int index, HashMap<Integer, Object> existence) {
		int leastConnectedIndex = 0;
		for (Integer gen : connections.get(index)) {
			if (existence.containsValue(gen)) continue;
			if (connections.get(gen).size() < connections.get(leastConnectedIndex).size() || (connections.get(gen).size() == connections.get(leastConnectedIndex).size() && RandomGenerator.createAleatoryBoolean(0.5))) 
				leastConnectedIndex = gen;
		} 
		return leastConnectedIndex;
	}

}
