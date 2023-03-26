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
	
	public void execute(Request request) {
		Map<String, Object> config = Builder.build(request);
		this.executor = new Executor(config);
		executor.run();
	}
	
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
