package control;

public interface Client {

	void paintResult(double[] generationAverage, double[] generationLeaders, double[] absoluteLeaders,
			double[] selectivePressure, String bestChromosomeToString);
}
