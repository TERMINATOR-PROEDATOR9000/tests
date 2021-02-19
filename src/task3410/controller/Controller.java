package task3410.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import task3410.model.Direction;
import task3410.model.GameObjects;
import task3410.model.Model;
import task3410.view.View;

public class Controller implements EventListener{

	private View view;
	private Model model;

	public Controller() {		
		view=new View(this);
		model=new Model();
		view.init();
		model.restart();
		view.setEventListener(this);
		model.setEventListener(this);
	}

	public static void main(String... args) {
		new Controller();			
	}

	@Override
	public void move(Direction direction) {
		model.move(direction);
		view.update();
	}

	@Override
	public void restart() {
		model.restart();
		JOptionPane.showMessageDialog(view, "Level restarted.");
		view.update();
	}

	@Override
	public void startNextLevel() {
		model.startNextLevel();
		view.update();
	}

	@Override
	public void levelCompleted(int level) {
		view.completed(level);
	}
	
	public GameObjects getGameObjects() {
		return model.getGameObjects();
	}
}
