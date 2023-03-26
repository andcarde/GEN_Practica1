package launcher;

import java.awt.EventQueue;

import graphic.Window;
import statistics.StatisticGenerator;

public class Main {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("test")) {
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
