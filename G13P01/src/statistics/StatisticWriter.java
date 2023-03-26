package statistics;

import java.io.FileWriter;
import java.io.IOException;

public class StatisticWriter {
	
    public static void main(String[] args) {
        try {
            // Crea un objeto FileWriter con el nombre de archivo "archivo.txt"
            FileWriter writer = new FileWriter("archivo.txt", true);

            // Escribe texto en el archivo
            writer.write("¡Hola, mundo!");

            // Cierra el objeto FileWriter
            writer.close();
            System.out.println("Archivo creado con éxito.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    public void writeTest(String elitismRate, String selectionMethod, String crossoverMethod,
    		String mutationMethod, String average) {
		// TODO Falta
	}
    
	public void writeTables(String[] crossoverNames, String[] crossoverAverages, String[] mutationNames,
			String[] mutationAverages) {
		// TODO Falta
	}

	public void writeBest(String bestElitismRate, String bestSelectionMethod, String bestCrossoverMethod,
			String bestMutationMethod, String bestAverage) {
		// TODO Falta
	}
}
