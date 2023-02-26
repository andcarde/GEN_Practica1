package graphic;

import java.awt.EventQueue;
import java.util.Map;

import model.Builder;
import model.Executor;

public class Controller {

	private static Controller controller;
	private Window window;
	
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
		this.window.paintResult(
				executor.getGenerationAverage(),
				executor.getGenerationLeaders(),
				executor.getAbsoluteLeaders(),
				executor.getBestChromosomeToString());
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.setVisible(true);
					Controller.getInstance().setWindow(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setWindow(Window window) {
		this.window = window;
	}
}
