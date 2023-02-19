package model;
import graphic.Window;

public class Controller {
	
	private Window win;
	private Executor exe;
	
	public Controller() {
		win = new Window();
		win.setVisible(true);
	}

}
