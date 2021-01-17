package task3410.model;

import java.awt.Color;
import java.awt.Graphics;

public class Home extends GameObject{

	public Home(int x, int y) {
		super(x, y);
		this.setWidth(2);
		this.setHeight(2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawArc(this.getX()-1, this.getY()-1, this.getWidth(), this.getHeight(), 0, 360);
	}

}
