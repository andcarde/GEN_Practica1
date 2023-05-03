package launcher;

import java.awt.EventQueue;

import graphic.Window;
import statistics.StatisticGenerator;

public class Main {
	
	/***
	 * If the option "test" is set it executed the testing mode. In other case the standard window
	 * view that allows user configuration and graphic visualization.
	 * @param args
	 */
	
	public static void main(String[] args) {
		if (args.length > 0 && args[0].equalsIgnoreCase("test")) {
			new StatisticGenerator().run();
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Window window = new Window();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
