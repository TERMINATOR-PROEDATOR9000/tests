package task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends CollisionObject implements Movable {

	public Box(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawRect(
				this.getX() - Model.FIELD_CELL_SIZE / 2,
				this.getY() - Model.FIELD_CELL_SIZE / 2,
				this.getWidth(),
				this.getHeight());
		g.drawLine(
				this.getX() - Model.FIELD_CELL_SIZE / 2,
				this.getY() - Model.FIELD_CELL_SIZE / 2,
				this.getX() + Model.FIELD_CELL_SIZE - Model.FIELD_CELL_SIZE / 2,
				this.getY() + Model.FIELD_CELL_SIZE - Model.FIELD_CELL_SIZE / 2);
		g.drawLine(
				this.getX() + Model.FIELD_CELL_SIZE - Model.FIELD_CELL_SIZE / 2,
				this.getY() - Model.FIELD_CELL_SIZE / 2,
				this.getX() - Model.FIELD_CELL_SIZE / 2,
				this.getY() + Model.FIELD_CELL_SIZE - Model.FIELD_CELL_SIZE / 2);
	}

	@Override
	public void move(int x, int y) {
		this.setX(this.getX() + x);
		this.setY(this.getY() + y);
	}

}
