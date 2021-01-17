package task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends CollisionObject {

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.decode("#873e23"));
		g.fillRect(
				this.getX() - Model.FIELD_CELL_SIZE / 2,
				this.getY() - Model.FIELD_CELL_SIZE / 2,
				this.getWidth(),
				this.getHeight());
	}

}
