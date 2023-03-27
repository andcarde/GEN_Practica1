package control;

import java.util.Map;

import model.Builder;
import model.Executor;

public class Controller {

	private static Controller controller;
	private Executor executor;
	
	private Controller() {}
	
	public static Controller getInstance() {
		if (Controller.controller == null)
			Controller.controller = new Controller();
		return Controller.controller;
	}
	
	/***
	 * Given a request, it used the Builder (utility class) to obtain a configuration
	 * that is used to the constructor of the executor. Then the genetic algorithm runs
	 * while the executor run() function is called.
	 * @param request
	 */
	public void execute(Request request) {
		Map<String, Object> config = Builder.build(request);
		this.executor = new Executor(config);
		executor.run();
	}
	
	/***
	 * Received a client who wants to receive and update of the view with
	 * the parameters of generation average, generation leaders, absolute leaders,
	 * selective pressure and the best chromosome in a print line form.
	 * @param client
	 */
	public void updateView(Client client) {
		client.paintResult(
				executor.getGenerationAverage(),
				executor.getGenerationLeaders(),
				executor.getAbsoluteLeaders(),
				executor.getSelectivePressure(),
				executor.getBestChromosomeToString()
		);
	}
}
