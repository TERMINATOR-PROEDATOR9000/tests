package task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends CollisionObject implements Movable {

	public Player(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillArc(
				this.getX() - Model.FIELD_CELL_SIZE / 2,
				this.getY() - Model.FIELD_CELL_SIZE / 2,
				this.getWidth(),
				this.getHeight(),
				0,
				360);
	}

	@Override
	public void move(int x, int y) {
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
	}

}
