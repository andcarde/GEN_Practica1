package graphic;

import java.util.Map;

import model.Builder;
import model.Executor;

public class Controller {

	private static Controller controller;
	
	private Controller() {}
	
	public static Controller getInstance() {
		if (Controller.controller == null)
			Controller.controller = new Controller();
		return Controller.controller;
	}
	
	public void execute(Request request) {
		Map<String, Object> config = Builder.build(request);
		Executor executor = new Executor(config);
		executor.run();
	}
}
