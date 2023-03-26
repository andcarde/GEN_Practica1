package statistics;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StatisticWriter {
	
	private BufferedWriter writer;
	
	public StatisticWriter() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("prueba.txt", true), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void writeTest(String elitismRate, String selectionMethod, String crossoverMethod,
    		String mutationMethod, String average) {
		try {
			writer.append("Con el elitismo al " + elitismRate + "%" + ", la selecci�n " 
				+ selectionMethod + ", el cruce " + crossoverMethod + "\r\n y la mutaci�n " + mutationMethod 
				+ " da un valor medio en 10 pruebas de " + average + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public void writeTables(String[] crossoverNames, String[] crossoverAverages, String[] mutationNames,
			String[] mutationAverages) {
		try {
			
			writer.append("\r\n");
			writer.append("--------------------------- RANKING DE CRUCES -------------------------------\r\n");
			for (int i = 0; i < crossoverAverages.length; i++) {
				writer.write((i+1)+"� Posici�n: " + crossoverNames[i] + ". Valor: " + crossoverAverages[i] + "\r\n");
			}
			writer.append("-----------------------------------------------------------------------------\r\n");
			writer.append("\r\n");
			writer.append("--------------------------- RANKING DE MUTACIONES -------------------------------\r\n");
			for (int i = 0; i < mutationAverages.length; i++) {
				writer.append((i+1)+"� Posici�n: " + mutationNames[i] + ". Valor: " + mutationAverages[i] + "\r\n");
			}
			writer.append("-----------------------------------------------------------------------------\r\n");
			writer.append("\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeBest(String bestElitismRate, String bestSelectionMethod, String bestCrossoverMethod,
			String bestMutationMethod, String bestAverage) {
		try {
			writer.append("-----------------------------------------------------------------------------\r\n");
			writer.write("Con el elitismo al " + bestElitismRate + "%" + ", la selecci�n " 
				+ bestSelectionMethod + ", el cruce " + bestCrossoverMethod + "\r\n y la mutaci�n " + bestMutationMethod 
				+ " hemos obtenido el mejor valor, el cual es " + bestAverage + "\r\n");
			writer.append("-----------------------------------------------------------------------------\r\n");
			writer.append("\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
