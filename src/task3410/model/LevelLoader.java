package task3410.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {

	private Path levels;

	public LevelLoader(Path levels) {
		super();
		this.levels = levels;
	}

	public GameObjects getLevel(int level) {
		/* int val = Model.FIELD_CELL_SIZE / 2;
		 * Home home = new Home(val, val);
		 * Box box = new Box(val, val);
		 * Player player = new Player(val, val);
		 * Wall wall_1 = new Wall(val, val);
		 * Wall wall_2 = new Wall(val, val);
		 * Wall wall_3 = new Wall(val, val);
		 * GameObjects result = new GameObjects(
		 * new HashSet<Wall>(Arrays.asList(wall_1, wall_2, wall_3)),
		 * new HashSet<Box>(Arrays.asList(box)), new HashSet<Home>(Arrays.asList(home)),
		 * player); */
		int ll;
		if (level > 60) {
			ll = level % 60;
			if(ll==0) {
				ll=60;
			}
		} else {
			ll = level;
		}
		//System.out.println(ll);
		try {
			List<String> loadedFile = Files.readAllLines(Paths.get("res/level.txt"));
			List<String> lev = new ArrayList<String>();
			String line = "";
			for (int i = 0; i < loadedFile.size() - 1; i++) {
				if (loadedFile.get(i).equals("*************************************")) {
					i += 8;
				}
				line += loadedFile.get(i) + "\n";
				line.trim();
				if (loadedFile.get(i + 1).equals("*************************************")) {
					line.trim();
					lev.add(line);
					line = "";
				}
			}
			Set<Wall> walls = new HashSet<Wall>();
			Set<Box> boxes = new HashSet<Box>();
			Set<Home> homes = new HashSet<Home>();
			Player player2 = null;
			int dX = Model.FIELD_CELL_SIZE;
			int dY = Model.FIELD_CELL_SIZE;
			// int count=0;
			// for (int i = 0; i < lev.size(); i++) {
			String loadedLevel = lev.get(ll - 1);
			String[] lines = loadedLevel.split("\n");
			/* for (int i = 0; i < lines.length; i++) {
			 * System.out.println(++count+" "+lines[i]);
			 * } */
			for (int j = 0; j < lines.length; j++) {
				dX = Model.FIELD_CELL_SIZE;
				for (int q = 0; q < lines[j].length(); q++) {
					dX += Model.FIELD_CELL_SIZE;
					char value = lines[j].charAt(q);
					switch (value) {
					case 'X': {
						walls.add(new Wall(dX, dY));
						break;
					}
					case '*': {
						boxes.add(new Box(dX, dY));
						break;
					}
					case '.': {
						homes.add(new Home(dX, dY));
						break;
					}
					case '@': {
						player2 = new Player(dX, dY);
						break;
					}
					case '&': {
						homes.add(new Home(dX, dY));
						boxes.add(new Box(dX, dY));
						break;
					}
					}
				}
				dY += Model.FIELD_CELL_SIZE;
			}
			// }
			return new GameObjects(walls, boxes, homes, player2);
		} catch (Exception e) {

		}
		return null;
	}
}
