package task3410.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import task3410.controller.EventListener;
import task3410.model.Direction;
import task3410.model.GameObject;
import task3410.model.GameObjects;
import task3410.model.Model;

public class Field extends JPanel {

	private View view;
	private EventListener eventListener;

	public class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_LEFT): {
				eventListener.move(Direction.LEFT);
				break;
			}
			case (KeyEvent.VK_RIGHT): {
				eventListener.move(Direction.RIGHT);
				break;
			}
			case (KeyEvent.VK_UP): {
				eventListener.move(Direction.UP);
				break;
			}
			case (KeyEvent.VK_DOWN): {
				eventListener.move(Direction.DOWN);
				break;
			}
			case (KeyEvent.VK_R): {
				eventListener.restart();
				;
			}
			case (KeyEvent.VK_N): {
				eventListener.levelCompleted(Model.currentLevel);
			}
			}
		}
	}

	public Field(View view) {
		super();
		this.view = view;
		KeyHandler kh = new KeyHandler();
		this.addKeyListener(kh);
		this.setFocusable(true);
	}

	public void paint(Graphics g) {
		/* GameObject box = new Box(50, 50);
		 * box.draw(g);
		 * GameObject circ = new Player(100, 100);
		 * circ.draw(g);
		 * GameObject hom = new Home(200, 200);
		 * hom.draw(g);
		 * GameObject wall = new Wall(150, 150);
		 * wall.draw(g); */
		// g.setColor(Color.black);
		// g.fillRect(view.getX(), view.getY(), view.getWidth(), view.getHeight());
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		g.drawString(
				"Current level is: "
						+ String.valueOf(
								Model.currentLevel == 60 ? 60
										: Model.currentLevel % 60),
				10,
				450);
		try {
			GameObjects ggo = view.getGameObjects();
			for (GameObject gd : ggo.getAll()) {
				gd.draw(g);
			}
		} catch (NullPointerException e) {
			System.out.println("null catched");
		}
	}

	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}
}
