package statistics;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StatisticWriter {
	
	// Class used to perform the write in the destination file.
	private BufferedWriter writer;
	
	public StatisticWriter() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("prueba.txt", true), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	/***
	 * Writes in the destination file the result of the global test of a configuration (average of
	 * individual test).
	 * @param elitismRate
	 * @param selectionMethod
	 * @param crossoverMethod
	 * @param mutationMethod
	 * @param average
	 */
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
    
    /**
     * Writes in the destination file, the rankings of crossover methods and mutation methods.
     * @param crossoverNames
     * @param crossoverAverages
     * @param mutationNames
     * @param mutationAverages
     */
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
			writer.append("--------------------------- RANKING DE MUTACIONES ---------------------------\r\n");
			for (int i = 0; i < mutationAverages.length; i++) {
				writer.append((i+1)+"� Posici�n: " + mutationNames[i] + ". Valor: " + mutationAverages[i] + "\r\n");
			}
			writer.append("-----------------------------------------------------------------------------\r\n");
			writer.append("\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/***
	 * Writes the best configuration that has been found in the testing.
	 * @param bestElitismRate
	 * @param bestSelectionMethod
	 * @param bestCrossoverMethod
	 * @param bestMutationMethod
	 * @param bestAverage
	 */
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
