package task3410.model;

import java.nio.file.Paths;
import task3410.controller.EventListener;

public class Model {
	public static final int FIELD_CELL_SIZE = 20;
	private EventListener eventListener;
	public static int currentLevel = 1;
	private GameObjects gameObjects;
	private LevelLoader levelLoader = new LevelLoader(Paths.get("res/level.txt"));

	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	public GameObjects getGameObjects() {
		return gameObjects;
	}

	public void restartLevel(int level) {		
		gameObjects = levelLoader.getLevel(level);
	}

	public void restart() {
		restartLevel(currentLevel);
	}

	public void startNextLevel() {
		restartLevel(++currentLevel);
	}

	public void move(Direction direction) {
		if (checkWallCollision(gameObjects.getPlayer(), direction)) {
			return;
		}
		if (checkBoxCollisionAndMoveIfAvailable(direction)) {
			return;
		}
		switch (direction) {
		case UP: {
			gameObjects.getPlayer().move(0, -FIELD_CELL_SIZE);
			break;
		}
		case LEFT: {
			gameObjects.getPlayer().move(-FIELD_CELL_SIZE, 0);
			break;
		}
		case RIGHT: {
			gameObjects.getPlayer().move(FIELD_CELL_SIZE, 0);
			break;
		}
		case DOWN: {
			gameObjects.getPlayer().move(0, FIELD_CELL_SIZE);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		checkCompletion();
	}

	public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
		for (Wall w : gameObjects.getWalls()) {
			if (gameObject.isCollision(w, direction)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
		for (Box box : gameObjects.getBoxes()) {
			if (gameObjects.getPlayer().isCollision(box, direction)) {
				for (Box box_sec : gameObjects.getBoxes()) {
					if (!box.equals(box_sec)) {
						if (box.isCollision(box_sec, direction)) {
							return true;
						}
					}
					if (checkWallCollision(box, direction)) {
						return true;
					}
				}
				int dirX = direction == Direction.LEFT ? -FIELD_CELL_SIZE
						: (direction == Direction.RIGHT ? FIELD_CELL_SIZE : 0);
				int dirY = direction == Direction.UP ? -FIELD_CELL_SIZE
						: (direction == Direction.DOWN ? FIELD_CELL_SIZE : 0);
				box.move(dirX, dirY);
			}
		}
		return false;
	}

	public void checkCompletion() {
		int count = 0;
		for (Home h : gameObjects.getHomes()) {
			for (Box b : gameObjects.getBoxes()) {
				if (h.getX() == b.getX() && h.getY() == b.getY()) {
					count++;
				}
			}
		}
		if (count == gameObjects.getHomes().size()) {
			eventListener.levelCompleted(currentLevel);
		}
	}
}
