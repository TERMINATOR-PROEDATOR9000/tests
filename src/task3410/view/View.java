package task3410.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import task3410.controller.Controller;
import task3410.controller.EventListener;
import task3410.model.GameObjects;

public class View extends JFrame {

	/* private Controller controller;
	 * 
	 * public View(Controller controller){ 
	 * super(); 
	 * this.controller = controller;
	 * } */
	private Controller controller;
	private Field field;

	public View(Controller controller) {
		this.controller = controller;
	}

	public void init() {
		field = new Field(this);
		add(field);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setTitle("Сокобан");
		setVisible(true);
	}

	public void setEventListener(EventListener eventListener) {
		field.setEventListener(eventListener);;
	}
	
	public void update() {
		field.repaint();
	}
	
	public GameObjects getGameObjects() {
		return controller.getGameObjects();
	}
	
	public void completed(int level) {
		update();
		JOptionPane.showMessageDialog(this, "Level completed.");
		controller.startNextLevel();
	}

}
