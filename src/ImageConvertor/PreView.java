package ImageConvertor;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class PreView extends JPanel {

    Controller controller;
    int correction, w, h;

    public PreView(Controller controller) {
	this.controller = controller;
	double width = controller.getImageWidth() / 150;
	double height = controller.getImageHeight() / 150;
	correction = (int) Math.max(width, height);
	if (correction == 0) {
	    correction = 1;
	}
	try {
	    w = Math.abs(300 - controller.getImageWidth() / correction) / 2;
	    h = Math.abs(300 - controller.getImageHeight() / correction) / 4;
	} catch (Exception e) {
	    w = 1;
	    h = 1;
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	ImageIcon img = controller.getImageIcon();
	g.drawImage(img.getImage(), w, h, controller.getImageWidth() / correction,
		controller.getImageHeight() / correction, null);
    }

}
