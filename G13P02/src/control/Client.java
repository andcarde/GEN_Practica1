package control;

/***
 * Interface for the view. The Controller will use to paint the results that has been generator for the
 * model (the Executor is the central class of the model).
 */

public interface Client {

	void paintResult(double[] generationAverage, double[] generationLeaders, double[] absoluteLeaders, String bestChromosomeToString);
	
	void paintP3Graphics(double[] idealFunction, double[] obtainedFunction, double[] xvalues);
}
