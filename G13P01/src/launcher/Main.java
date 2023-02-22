package launcher;

import graphic.Controller;
import graphic.Window;

public class Main {

	public static void main(String[] args) {
		Window window = new Window(Controller.getInstance());
		window.setVisible(true);
	}
}
